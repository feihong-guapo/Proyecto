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

public class Form6 extends AppCompatActivity {
    private Button before;
    private Button next;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String selectedPlazasType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form6);
        next = findViewById(R.id.button22);
        before = findViewById(R.id.button21);
        radioGroup = findViewById(R.id.radioGroup6);
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
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Encuentra el RadioButton seleccionado por el ID
                radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    selectedPlazasType = radioButton.getText().toString();
                    // Ahora selectedFuelType contiene el texto del RadioButton seleccionado
                }
            }
        });
    }
    public void next() {
        Intent intent = new Intent(this, Form7.class);
        intent.putExtra("selectedPlazasType", selectedPlazasType);
        startActivity(intent);
    }
    public void before() {
        Intent intent = new Intent(this, Form3.class);
        startActivity(intent);
    }
}

