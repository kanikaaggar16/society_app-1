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

public class addmaintenance extends AppCompatActivity {
    private static final String TAG = "addmaintenance";
    EditText house,rupees,dategiven;
    Button back,submit,add;
    DatabaseReference dataRef;
    FirebaseAuth mAuth;
    int maxid = 0;
    maintenanceadd maintainadd;
    private DatePickerDialog.OnDateSetListener mDataSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmaintenance);

        house=(EditText)findViewById(R.id.home_id);
        rupees=(EditText)findViewById(R.id.amount_id);
        dategiven=(EditText)findViewById(R.id.date_id);
        submit=(Button)findViewById(R.id.bt1);
        back=(Button)findViewById(R.id.bt2);
        add=(Button)findViewById(R.id.bt3);
        maintainadd=new maintenanceadd();


        dategiven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog=new DatePickerDialog(
                        addmaintenance.this,
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
                dategiven.setText(date1);


            }
        };

        mAuth=FirebaseAuth.getInstance();


        dataRef= FirebaseDatabase.getInstance().getReference().child("MAINTENANCE");
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


                if (house.length() == 0) {
                    house.setError("enter house no");
                } else if (rupees.length() == 0) {
                    rupees.setError("enter amount");
                } else if (dategiven.length() == 0) {
                    dategiven.setError("enter date");
                } else
                {       maintainadd.setHouse_no(house.getText().toString());
                maintainadd.setAmount(rupees.getText().toString());
                maintainadd.setDate(dategiven.getText().toString());

                dataRef.child(String.valueOf(maxid + 1)).setValue(maintainadd).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(addmaintenance.this, "MAINTENANCE ADDED", Toast.LENGTH_SHORT).show();
//                            Intent intent=new Intent(addmaintenance.this,viewmaintenance.class);
//                            startActivity(intent);
                        } else {
                            Toast.makeText(addmaintenance.this, "MAINTENANCE  NOT  ADDED", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(addmaintenance.this, maintenancedetails.class);
                startActivity(intent);
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(addmaintenance.this,addmaintenance.class);
                startActivity(intent);
            }
        });
    }
}
