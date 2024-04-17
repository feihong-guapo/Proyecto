package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {
    private Button help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        help = findViewById(R.id.button8);

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                help();
            }
        });
    }
    public void help(){
        Intent intent = new Intent(this, empty_activity.class);
       // intent.putExtra("form1Added", true);
        startActivity(intent);

    }
}