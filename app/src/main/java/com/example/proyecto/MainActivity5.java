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

import com.example.proyecto.model.DataFormManager;
import com.example.proyecto.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    private ImageButton help;
    private ImageButton menu;
    private User createdUser;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        help = findViewById(R.id.imageButton6);
        menu = findViewById(R.id.imageButton7);
        createdUser = DataFormManager.getInstance().getUser();
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
        intent.putExtra("usuario", createdUser);
        startActivity(intent);

    }
    public void menu(){
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("usuario", createdUser);
        startActivity(intent);
    }
}