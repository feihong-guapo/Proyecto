package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.proyecto.model.DataFormManager;
import com.example.proyecto.model.User;

public class Form3 extends AppCompatActivity {
    private ImageButton before;
    private ImageButton next;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String selectedKmType;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);
        next = findViewById(R.id.imageButton10);
        before = findViewById(R.id.imageButton16);
        radioGroup = findViewById(R.id.radioGroup3);
        next.setEnabled(false);
        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){
                Toast.makeText(this, user.toString(), Toast.LENGTH_LONG);
            }
        }
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
                    selectedKmType = radioButton.getText().toString();
                    // Ahora selectedFuelType contiene el texto del RadioButton seleccionado
                    next.setEnabled(true);
                }
            }
        });
    }
    public void next() {
        Intent intent = new Intent(this, Form6.class);
        DataFormManager.getInstance().saveData("selectedKmType", selectedKmType);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }
    public void before() {
        Intent intent = new Intent(this, Form2.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }
}

