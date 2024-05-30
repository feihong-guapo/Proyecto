package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyecto.model.CarAdapter;
import com.example.proyecto.model.CarAdapter2;
import com.example.proyecto.model.Coche;
import com.example.proyecto.model.DataFormManager;
import com.example.proyecto.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Lista_Vista extends AppCompatActivity {
    private ImageButton repetir;
    private ImageButton menu;
    private RecyclerView recyclerView;
    private CarAdapter2 carAdapter;
    private List<Coche> carList;
    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vista);
        menu = findViewById(R.id.imageButton5);
        repetir = findViewById(R.id.imageButton3);
        recyclerView = findViewById(R.id.recyclerView12);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchCars();
        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");

            if (user != null ){
                Toast.makeText(this, user.toString(), Toast.LENGTH_LONG);
            }
        }
        repetir.setOnClickListener(v -> repetir());
        menu.setOnClickListener(v -> menu());
    }

    private void fetchCars() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://20.90.95.76/mostrarForm.php?modelo=" + DataFormManager.getInstance().getData("modelo") +
                "&combustible=" + DataFormManager.getInstance().getData("selectedFuelType") +
                "&kms=" + DataFormManager.getInstance().getData("selectedKmType") +
                "&tiempo=" + DataFormManager.getInstance().getData("selectedDailyType") +
                "&plazas=" + DataFormManager.getInstance().getData("selectedPlazasType") +
                "&uso=" + DataFormManager.getInstance().getData("selectedZoneType") +
                "&precioMaximo=" + DataFormManager.getInstance().getData("selectedPriceType")+
                "&is_user= "+ user.getUser_id();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Type listType = new TypeToken<List<Coche>>(){}.getType();
                    carList = new Gson().fromJson(response, listType);
                    carAdapter = new CarAdapter2(carList, this, user);
                    recyclerView.setAdapter(carAdapter);
                },
                error -> Toast.makeText(Lista_Vista.this, "Error fetching data", Toast.LENGTH_LONG).show());
        queue.add(stringRequest);
    }

    private void repetir() {
        Intent intent = new Intent(this, Form1.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }

    private void menu(){
        Intent intent = new Intent(this, Menu.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }
}