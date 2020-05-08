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

public class viewmember extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    viewmem mem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmember);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("MEMBER DETAILS");
        setSupportActionBar(toolbar);



        mem=new viewmem();
        listView=(ListView)findViewById(R.id.lsview);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("member");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.member_info,R.id.mem_info_id,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    mem=ds.getValue(viewmem.class);
                    list.add("NAME  "+mem.getMembername().toString()+"\n"+"EMAIL  "+mem.getMemberemail().toString()+" \n"+"NUMBER  "+mem.getMembernumber().toString());

                }


                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
