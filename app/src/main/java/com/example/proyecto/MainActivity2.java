package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.proyecto.model.User;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class MainActivity2 extends AppCompatActivity {
    private Button login;

    private EditText username;

    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        login = findViewById(R.id.button3);
        username = findViewById(R.id.editTextText);
        password = findViewById(R.id.editTextTextPassword3);
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                signingup();
            }
        });
    }
    public void signingup() {
        if(username.getText().toString().equals("") || password.getText().toString().equals("")){
            Toast.makeText(MainActivity2.this,"You have not entered one of the credentials required to LOGIN", Toast.LENGTH_LONG).show();
        }else if(password.getText().toString().length() <= 3){
            Toast.makeText(MainActivity2.this,"Password is too short. Session denied.", Toast.LENGTH_LONG).show();
        }else if(password.getText().toString().length() > 15){
            Toast.makeText(MainActivity2.this,"Password is too long. Session denied.", Toast.LENGTH_LONG).show();
        }else if(verifyUsername(username.getText().toString()) == false){
            Toast.makeText(MainActivity2.this,"The username has incorrect characters. Session denied.", Toast.LENGTH_LONG).show();
        }else {
            JSONObject params = new JSONObject();
            try {
                params.put("usuario", username.getText().toString());
                params.put("contrasena", password.getText().toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new ValidateUserTask().execute(params); // Pasa el JSON como parámetro
        }

    }
    public static boolean verifyUsername(String word){
        String regex = "^[a-zA-Z0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(word);

        return matcher.matches();
    }



    private class ValidateUserTask extends AsyncTask<JSONObject, Void, JSONObject> {
        @Override
        protected JSONObject doInBackground(JSONObject... jsonObjects) {
            JSONObject response = null;
            try {
                JSONObject params = jsonObjects[0];
                String url = "http://20.90.95.76/loginproj.php";
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
                    response = new JSONObject(jsonString);
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
        protected void onPostExecute(JSONObject result) {
            super.onPostExecute(result);
            if (result != null) {
                try {
                    String status = result.getString("status");

                    if (status.equals("OK")){
                        User user = new User();
                        JSONObject data = (JSONObject) result.get("data");
                        user.setUserData(data);
                        Intent intent = new Intent(MainActivity2.this, Menu.class);
                        intent.putExtra("usuario", user);
                        startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(MainActivity2.this,"Incorrect credentials. Session denied.", Toast.LENGTH_LONG).show();
            }
        }
    }
}