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

public class viewexpenses extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference ref;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    viewexp exp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewexpenses);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("EXPENSES DETAILS");
        setSupportActionBar(toolbar);


        exp=new viewexp();


        listView=(ListView)findViewById(R.id.lsview);

        database=FirebaseDatabase.getInstance();
        ref=database.getReference("EXPENSES");
        list=new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.expense_info,R.id.expense_info_id,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    exp=ds.getValue(viewexp.class);
                    list.add("PAID TO   "+exp.getGivento().toString()+" \n"+"DATE "+exp.getOn().toString()+" \n"+"AMOUNT  "+exp.getRuppes().toString());

                }


                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
