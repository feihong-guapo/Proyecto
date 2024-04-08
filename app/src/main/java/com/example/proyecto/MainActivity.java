package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button login;

    private Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.button);
        signup = findViewById(R.id.button2);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                login();
            }
        });
        signup.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                signup();
            }
        });
    }
    public void login() {
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
        finish();

    }
    public void signup() {
        Intent intent = new Intent(this,MainActivity3.class);
        startActivity(intent);
        finish();

    }
}