package com.example.proyecto.model;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.proyecto.CarDetailFragment;
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
        this.user = user;
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

        if (car.getImgs_src() != null){
            String rutaComp = car.getImgs_src() + "/1.jpg";
            Glide.with(context)
                    .load(rutaComp)
                    .into(holder.carImageView);
        }

        //holder.carImageView.setImageResource(getCarImageResource(car.getModelName()));

        // Load car image dynamically

        // Agregar Listener de clic al elemento de la lista
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener el coche seleccionado
                Coche selectedCar = carList.get(position);

                // Crear un Bundle para pasar los datos del coche al fragmento
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", user);
                bundle.putSerializable("coche", selectedCar);


                // Crear una instancia del fragmento CarDetailFragment y pasar los datos del coche
                CarDetailFragment carDetailFragment = new CarDetailFragment();
                carDetailFragment.setArguments(bundle);

                // Reemplazar el fragmento actual con el fragmento de detalle del coche
                FragmentTransaction transaction = ((FragmentActivity) view.getContext()).getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.flFragment, carDetailFragment);
                transaction.addToBackStack(null);  // Opcional: agregar la transacción a la pila de retroceso
                transaction.commit();
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
