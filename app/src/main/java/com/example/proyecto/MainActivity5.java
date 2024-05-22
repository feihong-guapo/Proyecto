package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    private ImageButton help;
    private ImageButton menu;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        help = findViewById(R.id.imageButton6);
        menu = findViewById(R.id.imageButton7);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu();
            }
        });
    }
    public void help(){
        Intent intent = new Intent(this, Form1.class);
       // intent.putExtra("form1Added", true);
        startActivity(intent);

    }
    public void menu(){
        Intent intent = new Intent(this, Menu.class);
        // intent.putExtra("form1Added", true);
        startActivity(intent);
    }
}