package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
public class Menu extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav.setOnNavigationItemSelectedListener(navListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    if (item.getItemId() == R.id.miSearch) {
                        selectedFragment = new MarketFragment();
                    } else if (item.getItemId() == R.id.miHeart) {
                        selectedFragment = new FavouritesFragment();
                    } else if (item.getItemId() == R.id.miChat) {
                        selectedFragment = new ChatFragment();
                    } else if (item.getItemId() == R.id.miProfile) {
                        selectedFragment = new ProfileFragment();
                    } else if (item.getItemId() == R.id.miHome){
                        selectedFragment = new MenuFragment();
                    }


                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                            selectedFragment).commit();

                    return true;
                }
            };

}


