package com.example.proyecto;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class Form1 extends AppCompatActivity {

    private ViewPager2 mViewPager;
    private List<Integer> mLayouts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form1);

        // Agregar los identificadores de diseño de cada formulario

    }
}



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_form1);
//


//        // Agregar los layouts de los formularios 2 y 3
//        mLayouts.add(R.layout.activity_form2);
//        mLayouts.add(R.layout.activity_form3);
//
//        // Configurar ViewPager2
//        mViewPager = findViewById(R.id.viewPager);
//        PageAdapter adapter = new PageAdapter(this, mLayouts);
//
//        // Verificar si el formulario 1 ya se ha agregado
//        if (adapter.getItemCount() == 0) {
//            // Si no se ha agregado, agréguelo al principio de la lista
//            mLayouts.add(0, R.layout.activity_form1);
//            adapter.notifyDataSetChanged(); // Notificar al adaptador sobre el cambio
//        }
//
//        mViewPager.setAdapter(adapter);
//    }
//    }
