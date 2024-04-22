package com.example.proyecto;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class SellActivity extends AppCompatActivity {
    private Button next;
    private EditText nombre;
    private EditText apellidos;
    private EditText telefono;
    private EditText edad;
    private EditText ciudad;
    private EditText direccion;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);

        next = findViewById(R.id.button9);
        nombre = findViewById(R.id.nameTxt);
        apellidos = findViewById(R.id.surnameTxt);
        telefono = findViewById(R.id.tlfTxt);
        ciudad = findViewById(R.id.cityTxt);
        direccion = findViewById(R.id.adressTxt);
        edad = findViewById(R.id.ageTxt);

        next.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                next();
            }
        });

        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){
                // Do something with the user object if needed
            }
        }
    }

    public void next() {
        try {
            user.setNombre(nombre.getText().toString());
            user.setApellidos(apellidos.getText().toString());
            user.setTelefono(telefono.getText().toString());
            user.setDireccion(direccion.getText().toString());
            user.setCiudad(ciudad.getText().toString());
            user.setEdad(Integer.parseInt(edad.getText().toString()));
            Log.d("JSON Params", user.getjSonparamsReg());

            // Execute AsyncTask to insert data
            new InsertDataTask().execute(user);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private class InsertDataTask extends AsyncTask<User, Void, String> {
        @Override
        protected String doInBackground(User... users) {
            User user = users[0];
            try {
                // Create the URL connection
                URL url = new URL("http://20.90.95.76/registaruser.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // Send JSON data to the server
                OutputStream os = conn.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
                String datos = user.getjSonparamsReg();
                writer.write(datos);
                writer.flush();
                writer.close();
                os.close();

                // Read the server response
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                conn.disconnect();

                return response.toString();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                {
                    if (result.equals("KO")){
                        Toast.makeText(SellActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
                    }
                    else{

                        Toast.makeText(SellActivity.this, "Registrado correctamente", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SellActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            } else {
                // Handle error if any
                Toast.makeText(SellActivity.this, "Error occurred", Toast.LENGTH_LONG).show();
            }
        }
    }
}
