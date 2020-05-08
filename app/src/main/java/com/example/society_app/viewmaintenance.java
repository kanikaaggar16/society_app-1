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

public class viewmaintenance extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    viewmtn mtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmaintenance);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("MAINTENANCE DETAILS");
        setSupportActionBar(toolbar);


        mtn=new viewmtn();
        listView=(ListView)findViewById(R.id.lsview);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("MAINTENANCE");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.maintenance_info,R.id.mtn_info_id,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    mtn=ds.getValue(viewmtn.class);
                    list.add("HOUSE N0  "+mtn.getHouse_no().toString()+"\n"+"AMOUNT  "+mtn.getAmount().toString()+" \n"+"DATE  "+mtn.getDate().toString());

                }


                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
