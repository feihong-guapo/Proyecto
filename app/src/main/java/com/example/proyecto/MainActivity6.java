package com.example.proyecto;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.proyecto.model.ImageAdapter;
import com.example.proyecto.model.Medidas;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity6 extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE

    };
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        //Pedimos permisos
        ActivityCompat.requestPermissions(MainActivity6.this, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
        // Acceso al disco interno
        File rutaInterna = new File(System.getenv("EXTERNAL_STORAGE")+"/Download");
        //Gestión de SD
        String rutaTruncadaInterna = getExternalFilesDirs(null)[0].getAbsolutePath()
                .replace("/Android/data/"+getPackageName()+"/files","/Download");
        String rutaTruncadaSD = getExternalFilesDirs(null)[1].getAbsolutePath()
                .replace("/Android/data/"+getPackageName()+"/files","/Download");
        File directorio;
        directorio = new File(Environment.getExternalStorageDirectory().getPath()+"/Download");
        File[] ficheros =  directorio.listFiles();
        Medidas[] medidas = new Medidas[ficheros.length];

        for (int i=0; i<ficheros.length; i++) {
            medidas[i] = new Medidas(i,  ficheros[i].getName(), ficheros[i]);
        }

        // Definimos el adaptador para crear la lista
        ImageAdapter adap=new ImageAdapter(medidas,this);
        GridView gridView = findViewById(R.id.gridView); // Cambia ListView a GridView
        gridView.setAdapter(adap);

    }
}
