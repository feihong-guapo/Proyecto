package com.example.proyecto;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.proyecto.model.Coche;
import com.example.proyecto.model.DataFormManager;
import com.example.proyecto.model.User;

public class CarDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_detail);

        // Obtener los datos pasados desde la actividad anterior
        Coche selectedCar = (Coche) getIntent().getSerializableExtra("coche");

        if (selectedCar != null) {
            // Crear una instancia del fragmento y pasar los datos
            CarDetailFragment2 carDetailFragment = new CarDetailFragment2();
            User createdUser = DataFormManager.getInstance().getUser();
            Bundle bundle = new Bundle();
            bundle.putSerializable("coche", selectedCar);
            bundle.putSerializable("usuario", createdUser);
            carDetailFragment.setArguments(bundle);

            // Iniciar la transacción del fragmento
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, carDetailFragment);
            transaction.commit();
        }
    }
}
