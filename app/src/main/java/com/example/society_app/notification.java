package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class notification extends AppCompatActivity {
    EditText text,number;
    Button send,back,sendmore;
    final int SEND_SMS_PERMISSION_REQUEST_CODE=1;
    private  static  final  int REQUEST_CALL=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("NOTIFICATION");
        setSupportActionBar(toolbar);
        text=(EditText)findViewById(R.id.message_id);
        number=(EditText)findViewById(R.id.phone_id);
        send=(Button)findViewById(R.id.send_id);
        back=(Button)findViewById(R.id.bt1);
        sendmore=(Button)findViewById(R.id.bt2);
        ImageView imagecall=findViewById(R.id.call_id);
        imagecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhonecall();
            }
        });

//        send.setEnabled(false);
        if(checkPermission(Manifest.permission.SEND_SMS)){
            send.setEnabled(true);

        }
        else{
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.SEND_SMS},SEND_SMS_PERMISSION_REQUEST_CODE);
        }
//        send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(text.length()==0){
//                    text.setError("enter text");
//                }
//                else if(number.length()==0){
//                    number.setError("enter number");
//                }
//                else{
//                    onSend();
//                }
//            }
//        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(notification.this,adminfeautres.class);
                startActivity(intent);
            }
        });
        sendmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(notification.this,notification.class);
                startActivity(intent);
            }
        });
    }


    private  void makePhonecall()
    {
        String  no=number.getText().toString();
        if(no.trim().length() >0){


            if(ContextCompat.checkSelfPermission(notification.this,
                    Manifest.permission.CALL_PHONE) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(notification.this,
                        new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);

            }
            else{
                String  dial="tel:"+no;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        }




        else{
            Toast.makeText(notification.this,"enter number",Toast.LENGTH_SHORT).show();
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


    public   void  onSend(View v){
        String phonenumber=number.getText().toString();
        String message=text.getText().toString();
        if(TextUtils.isEmpty(phonenumber))
        {
            Toast.makeText(notification.this,"enter number",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(message))
        {
            Toast.makeText(notification.this,"enter message",Toast.LENGTH_SHORT).show();

        }

        else {
        if(phonenumber==null || phonenumber.length()==0
                ||message==null && message.length()==0){
            number.setError("enter number");
            text.setError("enter text");
        }
        if (checkPermission(Manifest.permission.SEND_SMS)){
            SmsManager smsManager=SmsManager.getDefault();
            smsManager.sendTextMessage(phonenumber,null,message,null,null);
            Toast.makeText(notification.this,"Message sent",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(notification.this,"Message not  sent",Toast.LENGTH_SHORT).show();

        }
    }
}
    public  boolean checkPermission(String permission){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);
    }
}
