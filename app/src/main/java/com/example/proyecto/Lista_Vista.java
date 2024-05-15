package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.model.DataFormManager;

public class Lista_Vista extends AppCompatActivity {
    private Button repetir;
    private Button menu;
    private TextView text1;
    private TextView text2;
    private TextView text3;
    private TextView text4;
    private TextView text5;
    private TextView text6;
    private TextView text7;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vista);
        repetir = findViewById(R.id.button11);
        menu = findViewById(R.id.button12);
        text1 = findViewById(R.id.textView28);
        text2 = findViewById(R.id.textView29);
        text3 = findViewById(R.id.textView30);
        text4 = findViewById(R.id.textView31);
        text5 = findViewById(R.id.textView32);
        text6 = findViewById(R.id.textView33);
        text7 = findViewById(R.id.textView34);
        String valor1 = DataFormManager.getInstance().getData("modelo");
        String valor2 = DataFormManager.getInstance().getData("selectedDailyType");
        String valor3 = DataFormManager.getInstance().getData("selectedKmType");
        String valor4 = DataFormManager.getInstance().getData("selectedPlazasType");
        String valor5 = DataFormManager.getInstance().getData("selectedZoneType");
        String valor6 = DataFormManager.getInstance().getData("selectedFuelType");
        String valor7 = DataFormManager.getInstance().getData("selectedPriceType");

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
        Intent intent = new Intent(this, Form1.class);
        // intent.putExtra("form1Added", true);
        startActivity(intent);
    }
    private void menu(){
        Intent intent = new Intent(this, Menu.class);
        startActivity(intent);
    }
}