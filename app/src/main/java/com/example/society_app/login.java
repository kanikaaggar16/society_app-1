package com.example.society_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText email,password;
    Button login;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("LOGINFORM");

        setSupportActionBar(toolbar);
        fAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.et1);
        password = (EditText) findViewById(R.id.et2);
        login = (Button) findViewById(R.id.bt1);
        progressBar=findViewById(R.id.pb1);
//        getSupportActionBar().setTitle("LOGIN FORM");



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);

                String mail=email.getText().toString().trim();
                String pass=password.getText().toString().trim();
                if (TextUtils.isEmpty(mail)){
                    email.setError("email is required");
                    return;
                }
                if(TextUtils.isEmpty(pass)){
                    password.setError("password is required");
                    return;
                }
                if(pass.length()<6){
                    password.setError("password must be 6 characters or more");
                    return;
                }
                //authenticate the user

                fAuth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(login.this,"Logged in successfully",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.INVISIBLE);
                            Intent intent=new Intent(login.this,adminfeautres.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(login.this,"Wrong Credentials",Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        });

    }
}
