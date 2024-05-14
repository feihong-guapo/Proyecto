package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class Form8 extends AppCompatActivity {
    private Button before;
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form8);
        next = findViewById(R.id.button26);
        before = findViewById(R.id.button25);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                next();
            }
        });
        before.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                before();
            }
        });
    }
    public void next() {
        Intent intent = new Intent(this, MainActivity7.class);
        startActivity(intent);
    }
    public void before() {
        Intent intent = new Intent(this, Form7.class);
        startActivity(intent);
    }
}

