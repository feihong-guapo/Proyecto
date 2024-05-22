
package com.example.proyecto;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.proyecto.model.Coche;
import com.example.proyecto.model.User;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarDetailFragment2 extends Fragment {

    private ImageView carImageView;
    private TextView modelNameTextView;
    private TextView brandTextView;
    private TextView prizeTV;
    private TextView puertas;
    private TextView plazas;
    private TextView maletero;
    private TextView traccion;
    private TextView tamano;
    private TextView T1;
    private TextView T2;
    private TextView T3;
    private TextView combustible;
    private TextView motor;
    private TextView maxLCons;
    private TextView minLCons;
    private User user;
    private Coche coche;

    private ImageButton likeButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el diseño del fragmento
        View rootView = inflater.inflate(R.layout.fragment_car_detail2, container, false);

        // Obtener los datos del Argumento
        Bundle arguments = getArguments();
        if (arguments != null) {
            user = (User) arguments.getSerializable("user");
            coche = (Coche) arguments.getSerializable("coche");
            String modelName = arguments.getString("modelName");
            String brand = arguments.getString("brand");

            // Inicializar vistas
            carImageView = rootView.findViewById(R.id.carImageView);
            modelNameTextView = rootView.findViewById(R.id.modelTextView);
            brandTextView = rootView.findViewById(R.id.brandTextView);
            prizeTV = rootView.findViewById(R.id.Prize);
            puertas = rootView.findViewById(R.id.puertas);
            plazas = rootView.findViewById(R.id.plazas);
            maletero = rootView.findViewById(R.id.textView27);
            traccion = rootView.findViewById(R.id.textView25);
            tamano = rootView.findViewById(R.id.textView26);
            combustible = rootView.findViewById(R.id.combustible);
            T1 = rootView.findViewById(R.id.textViewT1);
            T2 = rootView.findViewById(R.id.textViewT2);
            T3 = rootView.findViewById(R.id.textViewT3);
            motor = rootView.findViewById(R.id.motor);
            maxLCons = rootView.findViewById(R.id.textViewConsMax);
            minLCons = rootView.findViewById(R.id.textViewConsMin);
            likeButton = rootView.findViewById(R.id.imageButton1);
            toggleLike(rootView.findViewById(R.id.imageButton1));
//        int carImageResource = getCarImageResource(modelName);
//        carImageView.setImageResource(carImageResource);
            modelNameTextView.setText(coche.getModelo());
            brandTextView.setText(coche.getMarca());
            prizeTV.setText(coche.getPrecioEuros() + " €");
            puertas.setText(coche.getPuertas() + "Puertas");
            plazas.setText(coche.getPlazas() + "Plazas");
            maletero.setText(coche.getMaleteroL() + "L");
            traccion.setText(coche.getTraccion());
            tamano.setText(coche.getAnchoCm() + " cm /" + coche.getLongitudCm() + " cm");
            combustible.setText(coche.getMotor().getCombustible());
            T1.setText(coche.getMotor().getTransmision1());
            T2.setText(coche.getMotor().getTransmision2());
            T3.setText(coche.getMotor().getTransmision3());
            motor.setText(coche.getMotor().getTipo());
            maxLCons.setText(coche.getMotor().getConsumoMixtoMaxL() + " L");
            minLCons.setText(coche.getMotor().getConsumoMixtoMinL() + " L");
            Button backButton = rootView.findViewById(R.id.button6);
            backButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finalizar la actividad actual para volver a la actividad anterior
                    getActivity().finish();
                }
            });

            likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        if (coche.isLiked()) {
                            coche.setLiked(false);

                        } else {
                            coche.setLiked(true);

                        }
                        toggleLike(rootView.findViewById(R.id.imageButton1));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });


        }

        return rootView;
    }

    private void guardarLikeOEliminarlo() {
        if(coche.isLiked()){
            new InsertDataTask().execute();
        }else{


        }

    }

    public void toggleLike(View view) {
        coche.setLiked(!coche.isLiked()); // Invierte el estado de "like"
        // Cambiar el color del botón según el estado de "like"
        if (coche.isLiked()) {
            ((ImageButton) view).setImageResource(R.drawable.corazon);
        } else {
            ((ImageButton) view).setImageResource(R.drawable.corazon_gris);
        }
        // Guardar el estado del "like" en la base de datos o servidor

    }


    private class InsertDataTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void ... voids) {

            try {
                // Create the URL connection
                URL url = new URL("http://20.90.95.76/guardarLike.php" + "?id_usuario=" + user.getUser_id() + "&id_coche=" + coche.getId_coche());
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
                    if (result.equals("KO")) {
                        Toast.makeText(getContext(), "Error al guardar el like", Toast.LENGTH_LONG).show();
                    } else {

                        Toast.makeText(getContext(), "like guardad correctamente", Toast.LENGTH_LONG).show();


                    }
                }
            } else {
                // Handle error if any
                Toast.makeText(getContext(), "Error occurred", Toast.LENGTH_LONG).show();
            }
        }
    }

}