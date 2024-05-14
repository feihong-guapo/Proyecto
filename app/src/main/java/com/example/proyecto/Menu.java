package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
<<<<<<< HEAD
=======
import android.widget.Button;
>>>>>>> registro
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> registro

import com.example.proyecto.model.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
public class Menu extends AppCompatActivity {
    private BottomNavigationView bottomNav;

    private User user;
    private ImageButton btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
<<<<<<< HEAD
        btn = findViewById(R.id.imageButton);
        bottomNav = findViewById(R.id.bottomNavigationView);
        bottomNav = findViewById(R.id.bottomNavigationView);
=======


        btn = findViewById(R.id.imageButton);
        bottomNav = findViewById(R.id.bottomNavigationView);

>>>>>>> registro
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){
                // Do something with the user object if needed
            }
        }


        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                assitencia();


            }
        });
    }



    private void assitencia(){
        Intent intent = new Intent(this, atencioncliente2.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
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
<<<<<<< HEAD
=======

                    // Pasa el objeto usuario al fragmento seleccionado
>>>>>>> registro
                    if (user != null && selectedFragment != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("usuario", user);
                        selectedFragment.setArguments(bundle);
                    }
<<<<<<< HEAD
=======

>>>>>>> registro
                    getSupportFragmentManager().beginTransaction().replace(R.id.flFragment,
                            selectedFragment).commit();

                    return true;
                }
<<<<<<< HEAD
=======

>>>>>>> registro
            };

}


