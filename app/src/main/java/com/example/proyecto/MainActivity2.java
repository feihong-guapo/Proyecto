package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        password = findViewById(R.id.editTextText2);
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
            new ValidateUserTask().execute(username.getText().toString(), password.getText().toString());
        }

    }
    public static boolean verifyUsername(String word){
        String regex = "^[a-zA-Z0-9]+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(word);

        return matcher.matches();
    }

    private void openNewActivity(String validUser) {
        //6. If the user has his credentials registered in the database we login to the layout ok.
        if (validUser.equals("ok")) {
            Toast.makeText(MainActivity2.this,"Logged in", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity4.class);
            startActivity(intent);
            finish();
            //7. If not we will go to the layout ko.
        } else if(validUser.equals("ko")){
            Toast.makeText(MainActivity2.this,"Incorrect credentials. Session denied.", Toast.LENGTH_LONG).show();
        }
    }
    //9. This function is used to convert a string to an xml document
    private static Document StringtoXMLDocumentTransformer(String xmlString)
    {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try
        {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlString)));
            return doc;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    //10. And here we will establish the function necessary to prove that the user has his credentials registered calling the php validatecount.
    private class ValidateUserTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String usuario = params[0];
            String contrasena = params[1];
            String url = "http://10.0.2.2/validacuenta.php";
            String resultado = null;
            try {
                URL direccion = new URL(url);
                HttpURLConnection conexion = (HttpURLConnection) direccion.openConnection();
                conexion.setRequestMethod("POST");
                conexion.setDoOutput(true);
                String datos = "usuario=" + usuario + "&contrasena=" + contrasena;
                OutputStream salida = conexion.getOutputStream();
                byte[] bytes = datos.getBytes(StandardCharsets.UTF_8);
                salida.write(bytes);
                salida.flush();
                salida.close();
                InputStream entrada = conexion.getInputStream();
                BufferedReader lector = new BufferedReader(new InputStreamReader(entrada));
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }
                entrada.close();
                conexion.disconnect();
                resultado = respuesta.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return resultado;
        }
        //10.On the postExecute we will prove what activity we wil open.
        @Override
        protected void onPostExecute(String resultado) {
            super.onPostExecute(resultado);
            Document doc = StringtoXMLDocumentTransformer(resultado);
            NodeList listaItem = (NodeList) doc.getElementsByTagName("respuesta");
            Element element = (Element) listaItem.item(0);
            String var_id = element.getElementsByTagName("estado").item(0).getTextContent();


            openNewActivity(var_id);
        }
    }
}