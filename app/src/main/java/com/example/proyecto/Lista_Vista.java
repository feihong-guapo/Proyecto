package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Lista_Vista extends AppCompatActivity {
    private Button repetir;
    private Button menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vista);
        repetir = findViewById(R.id.button11);
        menu = findViewById(R.id.button12);
        repetir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                repetir();
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                menu();
            }
        });
    }
    private void repetir() {
        Intent intent = new Intent(this, MainActivity7.class);
        // intent.putExtra("form1Added", true);
        startActivity(intent);
    }
    private void menu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}