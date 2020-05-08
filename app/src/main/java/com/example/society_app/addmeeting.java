package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class addmeeting extends AppCompatActivity {
    private static final String TAG = "addmeeting";
    EditText topic_on,date_on,time_on;
    Button submit,back,add;
    DatabaseReference dataRef;
    FirebaseAuth mAuth;
    int maxid = 0;
    private DatePickerDialog.OnDateSetListener mDataSetListener;


    meetingadd mtgadd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmeeting);

        topic_on=(EditText)findViewById(R.id.topic_id);
        date_on=(EditText)findViewById(R.id.date__id);
        time_on=(EditText)findViewById(R.id.time_id);
        submit=(Button)findViewById(R.id.bt1);
        back=(Button)findViewById(R.id.bt2);
        add=(Button)findViewById(R.id.bt3);


        mtgadd=new meetingadd();

        date_on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog=new DatePickerDialog(
                        addmeeting.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDataSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

            }
        });
        mDataSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Log.d(Tag,"ondatset"+year);
                month=month+1;
//
                Log.d(TAG, "onDateSet: mm/dd/yyyyy"+month+"/"+dayOfMonth +"/"+year);
                String date1=dayOfMonth+"/"+month+"/"+year;
                date_on.setText(date1);


            }
        };
        mAuth=FirebaseAuth.getInstance();


        dataRef= FirebaseDatabase.getInstance().getReference().child("MEETING");


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
                if (topic_on.length() == 0) {
                    topic_on.setError("enter details");

                } else if (date_on.length() == 0) {
                    date_on.setError("enter date");
                } else if (time_on.length() == 0) {
                    time_on.setError("enter time");
                }
                else {
                mtgadd.setTopic(topic_on.getText().toString());
                mtgadd.setDate(date_on.getText().toString());
                mtgadd.setTime(time_on.getText().toString());
                dataRef.child(String.valueOf(maxid + 1)).setValue(mtgadd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(addmeeting.this, "MEETING ADDED", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(addmeeting.this, "MEETING  NOT  ADDED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addmeeting.this,meetingdetails.class);
                startActivity(intent);
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addmeeting.this,addmeeting.class);
                startActivity(intent);
            }
        });


    }
}
