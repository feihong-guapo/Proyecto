package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity3 extends AppCompatActivity {
    private Button signup;

    private EditText email;

    private EditText username;

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        signup = findViewById(R.id.button4);
        email = findViewById(R.id.editTextText3);
        username = findViewById(R.id.editTextText4);
        password = findViewById(R.id.editTextText5);
        signup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                signingup();
                registUser();
            }
        });
    }

    public void signingup() {
        Intent intent = new Intent(this,SellActivity.class);
        startActivity(intent);
        finish();

    }

    private void registUser() {
        String emai1l = email.getText().toString();
        String user1 = username.getText().toString();
        String pass = password.getText().toString();
        try {
            URL url = new URL("https://10.0.0.2/insertacuenta.php");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Envía los datos al servidor
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            String datos = URLEncoder.encode("usuario", "UTF-8") + "=" + URLEncoder.encode(user1, "UTF-8") + "&" +
                    URLEncoder.encode("contrasena", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8") + "&" +
                    URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(emai1l, "UTF-8");
            writer.write(datos);
            writer.flush();
            writer.close();
            os.close();

            // Lee la respuesta del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String respuesta = "";
            String line;
            while ((line = br.readLine()) != null) {
                respuesta += line;
            }
            br.close();

            // Hacer algo con la respuesta del servidor (por ejemplo, mostrar un mensaje)
            Log.d("Respuesta del servidor", respuesta);

            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}