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

public class addexpenses extends AppCompatActivity {
    private static final String TAG = "addexpenses";
    EditText paid_to,amount,date;
    Button back,submit,add;
    FirebaseAuth mAuth;
    DatabaseReference dataRef;
    int maxid=0;
    expenseadd eadd;
    private DatePickerDialog.OnDateSetListener mDataSetListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexpenses);

        paid_to=(EditText)findViewById(R.id.send_to_id);
        amount=(EditText)findViewById(R.id.amount_id);
        date=(EditText)findViewById(R.id.date_id);
        submit=(Button)findViewById(R.id.bt1);
        add=(Button)findViewById(R.id.bt3);
        back=(Button)findViewById(R.id.bt2);
        eadd=new expenseadd();

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal=Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog=new DatePickerDialog(
                        addexpenses.this,
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
                date.setText(date1);


            }
        };

        mAuth=FirebaseAuth.getInstance();
        dataRef= FirebaseDatabase.getInstance().getReference().child("EXPENSES");


        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    maxid=(int) dataSnapshot.getChildrenCount();

                }
                else
                {
//                    /
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            submit.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    String givento=paid_to.getText().toString();
//                    String  ruppes=amount.getText().toString();
//                    String on=date.getText().toString();

                        if(paid_to.length()==0){
                            paid_to.setError("enter details");
                        }
                        else if(amount.length()==0){
                            amount.setError("enter amount");
                        }
                        else if(date.length()==0){
                            date.setError("enter date");
                        }

                    else{
                        eadd.setGivento(paid_to.getText().toString());
                        eadd.setRuppes(amount.getText().toString());
                        eadd.setOn(date.getText().toString());
                        dataRef.child(String.valueOf(maxid + 1)).setValue(eadd).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(addexpenses.this, "EXPENSES ADDED", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(addexpenses.this, "EXPENSES NOT  ADDED", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }


//                    dataRef.child("GIVEN TO").setValue(givento);
//                    dataRef.child("AMOUNT").setValue(ruppes);
//                    dataRef.child("DATE").setValue(on).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            if(task.isSuccessful()){
//                                Toast.makeText(addexpenses.this,"Details ADDED",Toast.LENGTH_SHORT).show();
//
//                            }
//                            else{
//                                Toast.makeText(addexpenses.this,"Details not ADDED",Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                    });

                }
            });
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(addexpenses.this,expenses.class);
                    startActivity(intent);
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(addexpenses.this,addexpenses.class);
                    startActivity(intent);
                }
            });

;    }
}
