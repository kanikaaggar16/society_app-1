package com.example.society_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class notice extends AppCompatActivity {
    ImageView addnotice,viewnotice;
    TextView add,view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        addnotice=(ImageView)findViewById(R.id.addnotice_id);
        viewnotice=(ImageView)findViewById(R.id.viewnotice_id);
        add=(TextView)findViewById(R.id.tv1);
        view=(TextView)findViewById(R.id.tv2);
        back=(Button)findViewById(R.id.bt1);




        addnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(notice.this,addnotice.class);
                startActivity(intent);

            }
        });

        viewnotice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(notice.this,viewnotice.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(notice.this,adminfeautres.class);
                startActivity(intent);
            }
        });
    }
}
