package com.example.society_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class maintenancedetails extends AppCompatActivity {
    ImageView addm,viewm;
    TextView add,view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maintenancedetails);

        addm=(ImageView)findViewById(R.id.addm_id);
        viewm=(ImageView)findViewById(R.id.viewm_id);
        add=(TextView)findViewById(R.id.tv1);
        view=(TextView)findViewById(R.id.tv2);
        back=(Button)findViewById(R.id.bt1);



        addm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(maintenancedetails.this,addmaintenance.class);
                startActivity(intent);
            }
        });

        viewm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(maintenancedetails.this,viewmaintenance.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(maintenancedetails.this,adminfeautres.class);
                startActivity(intent);
            }
        });



    }
}
