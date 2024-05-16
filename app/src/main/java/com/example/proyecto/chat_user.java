package com.example.proyecto;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import de.hdodenhof.circleimageview.CircleImageView;

public class chat_user  extends AppCompatActivity {
    private CircleImageView imagenPerfil;
    private TextView nombre;
    private RecyclerView rvMensaje;
    private EditText txtMensaje;
    private Button btnEnviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_user);
        imagenPerfil = (CircleImageView) findViewById(R.id.imagenPerfil);
        rvMensaje =  (RecyclerView) findViewById(R.id.rvMensajes);
        nombre = (TextView) findViewById(R.id.nombre);
        txtMensaje = (EditText) findViewById (R.id.txtMensaje);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);


    }


}


