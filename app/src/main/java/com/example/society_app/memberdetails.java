package com.example.society_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class memberdetails extends AppCompatActivity {
    ImageView add_member,view_member;
    TextView add,view;
    Button back;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memberdetails);
        add_member=(ImageView)findViewById(R.id.addmember_id);
        view_member=(ImageView)findViewById(R.id.viewmember_id);
        add=(TextView)findViewById(R.id.tv1);

        view=(TextView)findViewById(R.id.tv2);
        back=(Button)findViewById(R.id.bt1);



        add_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent=new Intent(memberdetails.this,addmember.class);
               startActivity(intent);
            }
        });
        view_member.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(memberdetails.this,viewmember.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(memberdetails.this,adminfeautres.class);
                startActivity(intent);
            }
        });
    }
}
