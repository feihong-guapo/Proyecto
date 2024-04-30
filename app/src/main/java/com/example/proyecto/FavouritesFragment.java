package com.example.proyecto;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class FavouritesFragment extends Fragment {

    private BottomNavigationView bottomNav;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Buscar el BottomNavigationView dentro de la vista inflada del fragmento
        bottomNav = view.findViewById(R.id.bottomNavigationView1);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        return view;
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    if (item.getItemId() == R.id.miFav) {
                        selectedFragment = new ShowFavourites();
                    } else if (item.getItemId() == R.id.miLists) {
                        selectedFragment = new ShowLists();
                    }

                    // Reemplazar el fragmento actual con el fragmento seleccionado
                    getParentFragmentManager().beginTransaction().replace(R.id.flFragment1,
                            selectedFragment).commit();

                    return true;
                }
            };
}
