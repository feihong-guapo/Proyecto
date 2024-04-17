package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    private Button help;
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        help = findViewById(R.id.button8);
        menu = findViewById(R.id.button10);

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
        Intent intent = new Intent(this, empty_activity.class);
       // intent.putExtra("form1Added", true);
        startActivity(intent);

    }
    public void menu(){
        Intent intent = new Intent(this, Menu.class);
        // intent.putExtra("form1Added", true);
        startActivity(intent);
    }
}