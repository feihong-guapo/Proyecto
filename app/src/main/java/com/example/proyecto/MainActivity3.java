package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.model.User;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity3 extends AppCompatActivity {
    private Button signup;

    private EditText email;

    private EditText username;

    private EditText password;

    private EditText confPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        signup = findViewById(R.id.button4);
        email = findViewById(R.id.editTextText3);
        username = findViewById(R.id.editTextText4);
        password = findViewById(R.id.editTextTextPassword2);
        confPassword = findViewById(R.id.editTextTextPassword);


        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!(confPassword.getText().toString().equals(password.getText().toString()))){
                    Toast.makeText(MainActivity3.this, "ambas contraseñas deben coincidir", Toast.LENGTH_LONG).show();

                }else if(password.getText().toString().length() <= 3){
                    Toast.makeText(MainActivity3.this,"Password is too short. Session denied.", Toast.LENGTH_LONG).show();
                }else if(password.getText().toString().length() > 15) {
                    Toast.makeText(MainActivity3.this, "Password is too long. Session denied.", Toast.LENGTH_LONG).show();
                }else{
                    nextPage();
                }


            }
        });
    }


    public void nextPage() {
        User user = new User();
        String emai1l = email.getText().toString();
        String user1 = username.getText().toString();
        String pass = password.getText().toString();
        try{
            user.setEmail(emai1l);
            user.setUsername(user1);
            user.setPassword(pass);
            Intent intent = new Intent(MainActivity3.this, SellActivity.class);
            intent.putExtra("usuario", user);
            startActivity(intent);
        }catch (Exception e){

        }

    }


}