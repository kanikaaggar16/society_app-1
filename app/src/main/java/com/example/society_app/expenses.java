package com.example.society_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class expenses extends AppCompatActivity {
    ImageView addexpense,viewexpense;
    TextView add,view;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);


        addexpense=(ImageView)findViewById(R.id.addexpense_id);
        viewexpense=(ImageView)findViewById(R.id.viewexpense_id);
        add=(TextView)findViewById(R.id.tv1);
        view=(TextView)findViewById(R.id.tv2);
        back=(Button)findViewById(R.id.bt1);


        addexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expenses.this,addexpenses.class);
                startActivity(intent);
            }
        });


        viewexpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(expenses.this,viewexpenses.class);
                startActivity(intent);
            }
        });
back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(expenses.this,adminfeautres.class);
        startActivity(intent);
    }
});
    }
}
