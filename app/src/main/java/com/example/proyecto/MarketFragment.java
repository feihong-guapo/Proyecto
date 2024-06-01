package com.example.proyecto;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.proyecto.model.CarAdapter;
import com.example.proyecto.model.Coche;
import com.example.proyecto.model.DataFormManager;
import com.example.proyecto.model.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MarketFragment extends Fragment {

    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Coche> carList;
    private Spinner brandSpinner;
    private Spinner typeSpinner;
    private Button applyFilterButton;
    private User user;

    private Button ayudame;

    private String[] carBrands = {"", "Peugeot", "Audi", "Bmw", "Volkswagen"};
    private String[] carTypes = {"", "Urbano", "Deportivo", "Monovolumen", "Berlina", "SUV 4x4", "Ranchera"};

    private JSONObject spinnerData = new JSONObject();

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("usuario");
        }
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_market, container, false);

        // Inicializar vistas
        recyclerView = rootView.findViewById(R.id.recyclerView);
        brandSpinner = rootView.findViewById(R.id.brandSpinner);
        typeSpinner = rootView.findViewById(R.id.typeSpinner);
        applyFilterButton = rootView.findViewById(R.id.applyFilterButton);
        carList = new ArrayList<>();
        carAdapter = new CarAdapter(carList, getContext(), user);
        ayudame = rootView.findViewById(R.id.button5);

        // Configurar el RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(carAdapter);

        // Configurar el adaptador para el spinner de marcas de coches
        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carBrands);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandAdapter);

        // Configurar el adaptador para el spinner de tipos de coches
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, carTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);

        DataFormManager.getInstance().saveData("user_id", String.valueOf(user.getUser_id()));
        try {
            spinnerData.put("marca", null);
            spinnerData.put("id", null);
            spinnerData.put("typeCar", null);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Configurar el botón de ayuda
        ayudame.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                next();
            }
        });

        // Configurar el botón para aplicar filtros
        applyFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aplicar el filtro
                try {
                    applyFiltr();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        new GetFilteredResultTask().execute(spinnerData);

        return rootView;
    }

    public void next() {
        Intent intent = new Intent(getActivity(), Form1.class);
        intent.putExtra("usuario", user);
        startActivity(intent);
    }

    public void applyFiltr() throws JSONException {
        String selectedBrand = brandSpinner.getSelectedItem().toString();
        String carType = typeSpinner.getSelectedItem().toString();
        if (!carType.equals("")) {
            spinnerData.put("typeCar", carType);
        }
        else{
            spinnerData.put("typeCar", null);
        }
        if (!selectedBrand.equals("")) {
            spinnerData.put("marca", selectedBrand);
        }        else{
            spinnerData.put("marca", null);
        }

        new GetFilteredResultTask().execute(spinnerData);
    }

    private class GetFilteredResultTask extends AsyncTask<JSONObject, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONObject... jsonObjects) {
            JSONArray response = null;
            try {
                JSONObject params = jsonObjects[0];
                String url = "http://20.90.95.76/getCarsWlikes.php" + "?id_user=" + user.getUser_id();
                URL apiUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(params.toString().getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
                outputStream.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder responseBuilder = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        responseBuilder.append(line);
                    }
                    inputStream.close();

                    String jsonString = responseBuilder.toString();
                    response = new JSONArray(jsonString);
                } else {
                    // Manejo de errores
                }
                connection.disconnect();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return response;
        }

        @Override
        protected void onPostExecute(JSONArray result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    // Parsear la respuesta JSON
                    List<Coche> carsList = new ArrayList<>();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject carJson = result.getJSONObject(i);
                        Coche car = new Coche();
                        car.setDataJson(carJson);
                        carsList.add(car);
                    }

                    // Actualizar la lista de coches en el RecyclerView
                    carAdapter.setCars(carsList);
                    carAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    // Manejar el caso en que ocurra un error al parsear el JSON
                    e.printStackTrace();
                }
            } else {
                // Manejar el caso en que el JSONArray result sea null
            }
        }
    }
}
