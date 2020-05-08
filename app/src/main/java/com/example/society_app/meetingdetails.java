package com.example.society_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class meetingdetails extends AppCompatActivity {

    ImageView addmeeting,viewmeeting;
    TextView add,view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meetingdetails);

        addmeeting=(ImageView)findViewById(R.id.addmeeting_id);
        viewmeeting=(ImageView)findViewById(R.id.viewmeeting_id);
        add=(TextView)findViewById(R.id.tv1);
        view=(TextView)findViewById(R.id.tv2);
        back=(Button)findViewById(R.id.bt1);



        addmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(meetingdetails.this,addmeeting.class);
                startActivity(intent);
            }
        });

        viewmeeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(meetingdetails.this,viewmeeting.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(meetingdetails.this,adminfeautres.class);
                startActivity(intent);
            }
        });


    }
}
