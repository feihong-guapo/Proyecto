package com.example.proyecto;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.proyecto.model.ImageAdapter;
import com.example.proyecto.model.Medidas;
import com.example.proyecto.model.User;

import java.io.File;


public class MainActivity6 extends Fragment {

    private User usuario;
    private static final int REQUEST_MEDIA_PERMISSIONS = 1;

    private static final String[] PERMISSIONS_MEDIA = {
            Manifest.permission.READ_MEDIA_IMAGES,
            Manifest.permission.READ_MEDIA_AUDIO,
            Manifest.permission.READ_MEDIA_VIDEO
    };

    private static final String[] LEGACY_PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.MANAGE_EXTERNAL_STORAGE
    };



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            usuario = (User) getArguments().getSerializable("usuario");
        }
        // Request permissions
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (!hasPermissions(PERMISSIONS_MEDIA)) {
                requestPermissions(PERMISSIONS_MEDIA, REQUEST_MEDIA_PERMISSIONS);
            } else {
                initializeApp(view);
            }
        } else {
            if (!hasPermissions(LEGACY_PERMISSIONS)) {
                requestPermissions(LEGACY_PERMISSIONS, REQUEST_MEDIA_PERMISSIONS);
            } else {
                initializeApp(view);
            }
        }
    }

    private boolean hasPermissions(String[] permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(requireContext(), permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    private void initializeApp(View view) {
        // Acceso al disco interno
        File rutaInterna = new File(System.getenv("EXTERNAL_STORAGE") + "/Download");

        // Gestión de SD
        String rutaTruncadaInterna = requireContext().getExternalFilesDirs(null)[0].getAbsolutePath()
                .replace("/Android/data/" + requireContext().getPackageName() + "/files", "/Download");
        String rutaTruncadaSD = requireContext().getExternalFilesDirs(null)[1].getAbsolutePath()
                .replace("/Android/data/" + requireContext().getPackageName() + "/files", "/Download");

        File directorio = new File(Environment.getExternalStorageDirectory().getPath() + "/Download");
        File[] ficheros = directorio.listFiles();
        Medidas[] medidas = new Medidas[ficheros.length];

        for (int i = 0; i < ficheros.length; i++) {
            medidas[i] = new Medidas(i, ficheros[i].getName(), ficheros[i]);
        }

        // Definimos el adaptador para crear la lista
        ImageAdapter adap = new ImageAdapter(medidas, requireContext());
        GridView gridView = view.findViewById(R.id.gridView);
        gridView.setAdapter(adap);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_MEDIA_PERMISSIONS) {
            boolean allGranted = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    allGranted = false;
                    break;
                }
            }

            if (allGranted) {
                initializeApp(getView());
            } else {
                // Handle the case where permissions are not granted
                // You might want to show a message to the user and close the app or limit functionality
            }
        }
    }
}
