package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class empty_activity extends AppCompatActivity {
    private ViewPager2 mViewPager;
    private PageAdapter mAdapter;
    private RadioButton yes;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);
        mViewPager = findViewById(R.id.viewPager);
        List<Integer> newLayouts = new ArrayList<>();
        newLayouts.add(R.layout.activity_form1);
        newLayouts.add(R.layout.activity_form2);
        newLayouts.add(R.layout.activity_form3);
        newLayouts.add(R.layout.activity_form4);
        newLayouts.add(R.layout.activity_form5);
        newLayouts.add(R.layout.activity_form6);
        newLayouts.add(R.layout.activity_form7);
        newLayouts.add(R.layout.activity_form8);
        // Crear una instancia de PageAdapter y pasar el contexto y la lista de diseños
        mAdapter = new PageAdapter(this, newLayouts);

        // Configurar el ViewPager2 con el adaptador
        mViewPager.setAdapter(mAdapter);

        // Obtener una referencia al RadioGroup y al RadioButton en el cuarto diseño
        View fourthLayout = LayoutInflater.from(this).inflate(R.layout.activity_form4, null);
        RadioGroup radioGroup = fourthLayout.findViewById(R.id.radioGroup4);
        yes = radioGroup.findViewById(R.id.radioButton11);

        // Registrar un listener para detectar cambios en el RadioButton
        yes.setOnClickListener(new View.OnClickListener() {
              @Override
            public void onClick(View v) {
                if (yes.isChecked()) {
                    // Si está seleccionado, actualiza los datos del ViewPager2 con los nuevos diseños
                    List<Integer> newLayouts = new ArrayList<>();
                    newLayouts.add(R.layout.activity_form5);
                    newLayouts.add(R.layout.activity_form6);
                    newLayouts.add(R.layout.activity_form7);
                    newLayouts.add(R.layout.activity_form9);
                    newLayouts.add(R.layout.activity_form8);
                    newLayouts.add(R.layout.activity_form10);
                    mAdapter.updateData(newLayouts);
                    Toast.makeText(getApplicationContext(), "RadioButton está seleccionado", Toast.LENGTH_SHORT).show();
                } else {
                    newLayouts.add(R.layout.activity_form6);
                    newLayouts.add(R.layout.activity_form7);
                    newLayouts.add(R.layout.activity_form9);
                    newLayouts.add(R.layout.activity_form8);
                    newLayouts.add(R.layout.activity_form10);
                    // Si no está seleccionado, no necesitas hacer nada específico
                    // ya que el ViewPager2 ya está configurado con los primeros layouts
                    Toast.makeText(getApplicationContext(), "RadioButton no está seleccionado", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
