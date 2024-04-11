package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SellActivity extends AppCompatActivity {
    private Button next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        next = findViewById(R.id.button9);
        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                next();
            }
        });

    }
    public void next() {
        Intent intent = new Intent(this,MainActivity5.class);
        startActivity(intent);
        finish();

    }
}