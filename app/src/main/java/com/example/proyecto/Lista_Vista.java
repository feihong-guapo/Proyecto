package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.example.proyecto.model.UserFormManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class Lista_Vista extends AppCompatActivity {
    private ImageButton repetir;
    private ImageButton menu;
    private RecyclerView recyclerView;
    private CarAdapter2 carAdapter;
    private List<Coche> carList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_vista);
        menu = findViewById(R.id.imageButton5);
        repetir = findViewById(R.id.imageButton3);
        recyclerView = findViewById(R.id.recyclerView12);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        fetchCars();

        repetir.setOnClickListener(v -> repetir());
        menu.setOnClickListener(v -> menu());
    }

    private void fetchCars() {
        RequestQueue queue = Volley.newRequestQueue(this);
        int userId = DataFormManager.getInstance().getIntData("user_id");
        String url = "http://20.90.95.76/mostrarForm.php?modelo=" + DataFormManager.getInstance().getData("modelo") +
                "&combustible=" + DataFormManager.getInstance().getData("selectedFuelType") +
                "&kms=" + DataFormManager.getInstance().getData("selectedKmType") +
                "&tiempo=" + DataFormManager.getInstance().getData("selectedDailyType") +
                "&plazas=" + DataFormManager.getInstance().getData("selectedPlazasType") +
                "&uso=" + DataFormManager.getInstance().getData("selectedZoneType") +
                "&precioMaximo=" + DataFormManager.getInstance().getData("selectedPriceType") +
                "&id_user=" + userId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    Log.d("Response", response);  // Log the complete response
                    Type listType = new TypeToken<List<Coche>>() {
                    }.getType();
                    try {
                        carList = new Gson().fromJson(response, listType);
                        carAdapter = new CarAdapter2(carList, this);
                        recyclerView.setAdapter(carAdapter);
                    } catch (JsonSyntaxException e) {
                        Log.e("GsonError", "Failed to parse JSON", e);
                        Toast.makeText(this, "Failed to parse data", Toast.LENGTH_LONG).show();
                    }
                },
                error -> Toast.makeText(Lista_Vista.this, "Error fetching data", Toast.LENGTH_LONG).show());
        queue.add(stringRequest);
    }

    private void repetir() {
        Intent intent = new Intent(this, Form1.class);
        startActivity(intent);
    }

    private void menu() {
        User user = DataFormManager.getInstance().getUser();
        if (user != null) {
            Intent intent = new Intent(this, Menu.class);
            intent.putExtra("usuario", user);
            startActivity(intent);
        } else {
            Log.e("Lista_Vista", "El objeto User es null");
            Toast.makeText(this, "Usuario no disponible", Toast.LENGTH_SHORT).show();
        }
    }
}
