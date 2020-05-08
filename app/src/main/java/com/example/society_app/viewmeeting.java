package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class viewmeeting extends AppCompatActivity {

        ListView listView;
        FirebaseDatabase database;
        DatabaseReference ref;
        ArrayList<String> list;
        ArrayAdapter<String> adapter;
        viewmtg mtg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmeeting);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("MEETING DETAILS");
        setSupportActionBar(toolbar);
        mtg=new viewmtg();
        listView=(ListView)findViewById(R.id.lsview);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("MEETING");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.meeting_info,R.id.mtg_info_id,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    mtg=ds.getValue(viewmtg.class);
                    list.add("TOPIC  "+mtg.getTopic().toString()+"\n"+"DATE  "+mtg.getDate().toString()+" \n"+"TIME  "+mtg.getTime().toString());

                }


                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}


