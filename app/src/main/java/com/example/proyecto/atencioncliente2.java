package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.proyecto.model.Incidence;
import com.example.proyecto.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

public class atencioncliente2 extends AppCompatActivity {

    private Button buttonSave;
    private Button buttonReset;

    private Spinner IncTypes;

    private EditText problema;

    private EditText extra;
    User user = new User();

    private String[] incsTyp = {"","Problemas con el chat",  "Problemas con el concesionario", "Problemas con el mercado", "Problemas de perfil", "Otros"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_atencioncliente2);

        problema = findViewById(R.id.editTextTextMultiLine1);
        extra = findViewById(R.id.editTextTextMultiLine2);

        buttonSave = findViewById(R.id.button13);
        buttonReset = findViewById(R.id.button14);

        IncTypes = findViewById(R.id.spinner);

        ArrayAdapter<String> brandAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, incsTyp);
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        IncTypes.setAdapter(brandAdapter);


        buttonSave.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {

                tryInsert();
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                tryInsert();
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

    public void tryInsert(){
        Date fechaHoraActual = new Date();

        // Define el formato deseado para la fecha y hora
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // Formatea la fecha y hora actual como un string
        String prob = problema.getText().toString();
        String type = IncTypes.getSelectedItem().toString();
        String date = formato.format(fechaHoraActual);
        String more = extra.getText().toString();
        if ((prob.equals(""))||(type.equals(""))){
            Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_LONG).show();
        }else{
            try{

                Incidence inc = new Incidence();
                inc.setIncidence(prob);
                inc.setCategoria(type);
                inc.setDate(date);
                inc.setExtra(more);
                inc.setUser_id(user.getUser_id());


                new InsertDataTask().execute(inc);


            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }

    private class InsertDataTask extends AsyncTask<Incidence, Void, String> {
        @Override
        protected String doInBackground(Incidence... users) {
            Incidence inc = users[0];
            try {
                // Create the URL connection
                URL url = new URL("http://20.90.95.76/saveInc.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
                conn.setRequestProperty("Accept", "application/json");
                conn.setDoOutput(true);

                // Send JSON data to the server
                OutputStream os = conn.getOutputStream();
                OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
                String datos = inc.getJsonParams();
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
                        Toast.makeText(atencioncliente2.this, "Error occurred", Toast.LENGTH_LONG).show();
                    }
                    else{

                        Toast.makeText(atencioncliente2.this, "Registrado correctamente", Toast.LENGTH_LONG).show();

                    }
                }
            } else {
                // Handle error if any
                Toast.makeText(atencioncliente2.this, "Error occurred", Toast.LENGTH_LONG).show();
            }
        }
    }
}