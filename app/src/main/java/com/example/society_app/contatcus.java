package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class contatcus extends AppCompatActivity {
    TextView query;
    ImageView call;
    private  static  final  int REQUEST_CALL=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contatcus);
        query=(TextView)findViewById(R.id.query_id);
        call=(ImageView)findViewById(R.id.my_id);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhonecall();
            }
        });



    }
    private  void makePhonecall()
    {


            if(ContextCompat.checkSelfPermission(contatcus.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(contatcus.this,
                        new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }
            else{
                String  dial="tel:"+"9924999662";
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }







    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_CALL){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makePhonecall();
            }
            else {
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
