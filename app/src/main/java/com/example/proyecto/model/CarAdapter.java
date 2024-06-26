package com.example.proyecto.model;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.CarDetailActivity;
import com.example.proyecto.R;

import java.util.List;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder> {

    private List<Coche> carList;

    private User user;
    private Context context; // Necesitamos el contexto para iniciar una nueva actividad

    public CarAdapter(List<Coche> carList) {
        this.carList = carList;
    }

    // Constructor que acepta el contexto
    public CarAdapter(List<Coche> carList, Context context, User user) {
        this.carList = carList;
        this.context = context;
    }


    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        Coche car = carList.get(position);
        holder.modelNameTextView.setText(car.getModelo());
        holder.brandTextView.setText(car.getMarca());
        holder.yearTextView.setText(String.valueOf(car.getPrecioEuros()));
        //holder.carImageView.setImageResource(getCarImageResource(car.getModelName()));

        // Load car image dynamically

        // Agregar Listener de clic al elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el coche seleccionado
                Coche selectedCar = carList.get(position);

                // Crear un Intent para abrir la nueva actividad y pasar los datos del coche
                Intent intent = new Intent(context, CarDetailActivity.class);
                intent.putExtra("coche", selectedCar);
//                intent.putExtra("modelName", selectedCar.getModelName());
//                intent.putExtra("brand", selectedCar.getBrand());
//                intent.putExtra("year", selectedCar.getYear());

                // Iniciar la nueva actividad
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView modelNameTextView, brandTextView, yearTextView;
        public ImageView carImageView;

        public CarViewHolder(View view) {
            super(view);
            modelNameTextView = view.findViewById(R.id.brandTextView);
            brandTextView = view.findViewById(R.id.brandTextView);
            yearTextView = view.findViewById(R.id.yearTextView);
            carImageView = view.findViewById(R.id.carImageView);

        }
    }

    // Method to get the image resource based on the car model name
    private int getCarImageResource(String modelName) {
        switch (modelName) {
            case "Corolla":
                return R.drawable.corolla;
            case "Civic":
                return R.drawable.civic;
            case "Accord":
                return R.drawable.accord;
            // Add cases for other car models if available
            default:
                return R.drawable.ic_launcher_background;
        }
    }
    public void setCars(List<Coche> carList) {
        this.carList = carList;
        notifyDataSetChanged();
    }

}