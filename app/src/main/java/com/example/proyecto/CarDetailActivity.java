package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CarDetailActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cardetail);
        // Obtener los datos del Intent
        Intent intent = getIntent();
        String modelName = intent.getStringExtra("modelName");
        String brand = intent.getStringExtra("brand");


        ImageView carImageView = findViewById(R.id.carImageView); // Asegúrate de tener un ImageView en tu layout con el id carImageView
        TextView modelNameTextView = findViewById(R.id.modelTextView); // Asegúrate de tener un TextView en tu layout con el id modelNameTextView
        TextView brandTextView = findViewById(R.id.brandTextView); // Asegúrate de tener un TextView en tu layout con el id brandTextView

        // Configurar la imagen del coche (asumiendo que tienes una función para obtener el recurso de imagen basado en el nombre del modelo)
        int carImageResource = getCarImageResource(modelName);
        carImageView.setImageResource(carImageResource);




        // Configurar el nombre del modelo y la marca
        modelNameTextView.setText(modelName);
        brandTextView.setText(brand);

    }
    // Method to get the image resource based on the car model name
    private int getCarImageResource(String modelName) {
        // Construye el nombre del archivo de imagen basado en el nombre del modelo
        String imageName = modelName.toLowerCase(); // Asegúrate de que los nombres de los archivos de imagen estén en minúsculas y sin espacios
        imageName = imageName.replace(" ", "_"); // Reemplaza los espacios con guiones bajos si es necesario

        // Obtiene el identificador del recurso de imagen correspondiente
        return getResources().getIdentifier(imageName, "drawable", getPackageName());
    }

}