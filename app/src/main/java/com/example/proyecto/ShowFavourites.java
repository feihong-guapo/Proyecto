package com.example.proyecto;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.model.CarAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class ShowFavourites extends Fragment {
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Car> carList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }
}