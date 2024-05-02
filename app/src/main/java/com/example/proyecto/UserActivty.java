package com.example.proyecto;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserActivty extends AppCompatActivity {
    private TextView email;

    private TextView tlf;

    private TextView nombre;

    private TextView apellidos;

    private TextView codigoPos;

    private Button changePhoto;

    private Button updateUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        email = findViewById(R.id.email);
        tlf = findViewById(R.id.phone);
        nombre = findViewById(R.id.first_name);
        apellidos = findViewById(R.id.last_name);
        codigoPos = findViewById(R.id.postal_code);

        updateUser =findViewById(R.id.change_password_button);


        updateUser.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                JSONObject json = new JSONObject();
                try {
                    json.put("nombre", nombre.getText().toString());
                    json.put("apellidos", apellidos.getText().toString());
                    json.put("email", email.getText().toString());
                    json.put("telefono", tlf.getText().toString());
                    json.put("ciudad", codigoPos.getText().toString());
                    json.put("id_usuario", 1);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        });



    }
    private class InsertDataTask extends AsyncTask<JSONObject, Void, String> {
        @Override
        protected String doInBackground(JSONObject... users) {
            JSONObject inc = users[0];
            try {
                // Create the URL connection
                URL url = new URL("http://20.90.95.76/updUser.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("PUT");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // Send JSON data to the server
                OutputStream os = conn.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
                String datos = inc.toString();
                Log.d("fdasd",datos);
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
                        Toast.makeText(UserActivty.this, "Error occurred", Toast.LENGTH_LONG).show();
                    }
                    else{

                        Toast.makeText(UserActivty.this, "Actualizado correctamente", Toast.LENGTH_LONG).show();

                    }
                }
            } else {
                // Handle error if any
                Toast.makeText(UserActivty.this, "Error occurred", Toast.LENGTH_LONG).show();
            }
        }
    }
}
