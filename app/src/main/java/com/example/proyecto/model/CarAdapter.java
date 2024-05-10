package com.example.proyecto.model;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>{
    private Context context;
    private List<Car> carList;

    public CarAdapter(Context context, List<Car> carList) {
        this.context = context;
        this.carList = carList;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_car, parent, false);
        return new CarViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarViewHolder holder, int position) {
        Car car = carList.get(position);
        holder.itemView.setOnClickListener(v -> {
            //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(//car.getUrl()));
            //context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public static class CarViewHolder extends RecyclerView.ViewHolder {
        public TextView carName;
        public ImageView carImage;

        public CarViewHolder(View itemView) {
            super(itemView);
            carName = itemView.findViewById(R.id.textViewCarName);
            carImage = itemView.findViewById(R.id.imageViewCar);
        }
    }
}
