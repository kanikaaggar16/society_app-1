package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class addmember extends AppCompatActivity {
    EditText email, name, number;
    Button submit, back,add;
    DatabaseReference dataRef;
    FirebaseAuth mAuth;
    int maxid = 0;
    memberadd madd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmember);
        email = (EditText) findViewById(R.id.email_id);
        name = (EditText) findViewById(R.id.name_id);
        number = (EditText) findViewById(R.id.number_id);
        submit = (Button) findViewById(R.id.bt1);
        back = (Button) findViewById(R.id.bt2);
        add=(Button)findViewById(R.id.bt3);
        madd = new memberadd();

        mAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = mAuth.getCurrentUser();
//        String uid = user.getUid();


        dataRef = FirebaseDatabase.getInstance().getReference().child("member");

        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    maxid = (int) dataSnapshot.getChildrenCount();
                } else {
                    //
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                membername=name.getText().toString();
//                memberail=email.getText().toString();
//                membernumber=number.getText().toString();

                if (name.length() == 0) {
                    name.setError("enter name");
                } else if (email.length() == 0) {
                    email.setError("Add email");
                } else if (number.length() == 0) {
                    number.setError("enter number");
                }
                else {
                    madd.setMemberemail(email.getText().toString());
                    madd.setMembername(name.getText().toString());
                    madd.setMembernumber(number.getText().toString());


                    dataRef.child(String.valueOf(maxid + 1)).setValue(madd).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(addmember.this, "MEMBER ADDED", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(addmember.this, "MEMBER NOT  ADDED", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
//                dataRef.child("Name").setValue(mname);
//                dataRef.child("Email").setValue(memail);
//                dataRef.child("Phone Number").setValue(mnumber).addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//                        if(task.isSuccessful()){
//                            Toast.makeText(addmember.this,"Details Added",Toast.LENGTH_SHORT).show();
//                        }
//                        else{
//                            Toast.makeText(addmember.this,"DEtails  not Added",Toast.LENGTH_SHORT).show();
//                        }
//
//                    }
//                });
//
//
//
//
//
//
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addmember.this, memberdetails.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addmember.this,addmember.class);
                startActivity(intent);
            }
        });


    }
}
