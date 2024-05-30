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
import com.example.proyecto.CarDetailFragment2;
import com.example.proyecto.R;

import java.util.List;

public class CarAdapter2 extends RecyclerView.Adapter<CarAdapter2.CarViewHolder> {

    private List<Coche> carList;
    private Context context;
    private User user;

    public CarAdapter2(List<Coche> carList, Context context) {
        this.carList = carList;
        this.context = context;
    }

    public CarAdapter2(List<Coche> carList, Context context, User user) {
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
    public void onBindViewHolder(CarViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Coche car = carList.get(position);
        holder.modelNameTextView.setText(car.getModelo());
        holder.brandTextView.setText(car.getMarca());
        holder.yearTextView.setText(String.valueOf(car.getPrecioEuros()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Coche selectedCar = carList.get(position);

                Intent intent = new Intent(context, CarDetailActivity.class);
                intent.putExtra("coche", selectedCar);
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
            modelNameTextView = view.findViewById(R.id.modelTextView);
            brandTextView = view.findViewById(R.id.brandTextView);
            yearTextView = view.findViewById(R.id.yearTextView);
            carImageView = view.findViewById(R.id.carImageView);
        }
    }

    public void setCars(List<Coche> carList) {
        this.carList = carList;
        notifyDataSetChanged();
    }
}