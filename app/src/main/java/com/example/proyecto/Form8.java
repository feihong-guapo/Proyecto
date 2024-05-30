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

public class Form8 extends AppCompatActivity {
    private ImageButton before;
    private ImageButton next;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String selectedFuelType; // Variable para almacenar el tipo de combustible seleccionado
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form8);

        // Botones
        next = findViewById(R.id.imageButton13);
        before = findViewById(R.id.imageButton19);
        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){
                Toast.makeText(this, user.toString(), Toast.LENGTH_LONG);
            }
        }
        // RadioGroup y RadioButton
        radioGroup = findViewById(R.id.radioGroup8);
        next.setEnabled(false);

        // Establecer escuchadores para los botones
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

        // Escuchador para los cambios en el RadioGroup
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // Encuentra el RadioButton seleccionado por el ID
                radioButton = findViewById(checkedId);
                if (radioButton != null) {
                    selectedFuelType = radioButton.getText().toString();
                    // Ahora selectedFuelType contiene el texto del RadioButton seleccionado
                    next.setEnabled(true);
                }
            }
        });
    }

    public void next() {
        Intent intent = new Intent(this, MainActivity7.class);
        // Pasar el tipo de combustible seleccionado a la siguiente actividad
        DataFormManager.getInstance().saveData("selectedFuelType", selectedFuelType);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }

    public void before() {
        Intent intent = new Intent(this, Form7.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }
}
