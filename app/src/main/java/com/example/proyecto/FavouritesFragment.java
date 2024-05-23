package com.example.proyecto;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyecto.model.CarAdapter;
import com.example.proyecto.model.Coche;
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


public class FavouritesFragment extends Fragment {
    private RecyclerView recyclerView;
    private CarAdapter carAdapter;
    private List<Coche> carList;
    private User user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        if (bundle != null) {
            user = (User) bundle.getSerializable("usuario");
        }
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        recyclerView = rootView.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        carList = new ArrayList<>();
        carAdapter = new CarAdapter(carList, getContext(), user);
        recyclerView.setAdapter(carAdapter);
        loadFavouriteCars();
        return rootView;
    }

    private class GetFilteredResultTask extends AsyncTask<JSONObject, Void, JSONArray> {
        @Override
        protected JSONArray doInBackground(JSONObject... jsonObjects) {
            JSONArray response = null;
            try {
                JSONObject params = jsonObjects[0];
                String url = "http://20.90.95.76/mostrarFav.php";
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
                    Log.d("FavouritesFragment", "JSON Response: " + jsonString);  // Debug log
                    response = new JSONArray(jsonString);
                } else {
                    Log.e("FavouritesFragment", "HTTP Error: " + responseCode);  // Error log
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
            if (result != null && result.length() > 0) {
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
                    Log.d("FavouritesFragment", "Cars loaded: " + carsList.size());  // Debug log
                    carAdapter.setCars(carsList);
                    carAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.d("FavouritesFragment", "No cars found or result is null");  // Debug log
            }
        }
    }

    private void loadFavouriteCars() {
        JSONObject params = new JSONObject();
        try {
            params.put("id_user", user.getUser_id());
            Log.d("FavouritesFragment", "Params: " + params.toString());  // Debug log
        } catch (JSONException e) {
            e.printStackTrace();
        }
        new GetFilteredResultTask().execute(params);
    }
}
