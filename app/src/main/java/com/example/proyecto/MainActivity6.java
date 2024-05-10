package com.example.proyecto;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.example.proyecto.model.User;

public class MainActivity6 extends AppCompatActivity {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private GridView gridView;
    private List<File> imageFiles;
    private ImageAdapter imageAdapter;

    private User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        gridView = findViewById(R.id.gridView);

        imageFiles = new ArrayList<>();
        imageAdapter = new ImageAdapter();
        gridView.setAdapter(imageAdapter);

        Intent intent = getIntent();
        if (intent != null){
            user = (User) intent.getSerializableExtra("usuario");
            if (user != null ){

            }
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity6.this, "Haz clic en la imagen " + position, Toast.LENGTH_SHORT).show();
            }
        });

    checkPermissionsAndScanStorage();
    }

    private void checkPermissionsAndScanStorage() {
        // Verificar si se tienen los permisos de almacenamiento
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MainActivity6.this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        } else {
            // Si los permisos están concedidos, continuar con la exploración del almacenamiento
            scanStorageForImageFiles();
        }
    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_EXTERNAL_STORAGE) {
            // Verificar si se han concedido los permisos
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Los permisos fueron concedidos, continuar con la lógica de la aplicación
                scanStorageForImageFiles();
            } else {
                // Los permisos fueron denegados, mostrar un mensaje o realizar acciones adicionales
                Toast.makeText(this, "Los permisos fueron denegados.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void scanStorageForImageFiles() {
        File storageDirectory = Environment.getExternalStorageDirectory();
        imageFiles.clear();
        scanDirectoryForImageFiles(storageDirectory);
        imageAdapter.notifyDataSetChanged();
    }

    private void scanDirectoryForImageFiles(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    scanDirectoryForImageFiles(file);
                } else if (isImageFile(file)) {
                    imageFiles.add(file);
                }
            }
        }
    }

    private boolean isImageFile(File file) {
        String fileName = file.getName();
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("jpg") || extension.equalsIgnoreCase("jpeg")
                || extension.equalsIgnoreCase("png") || extension.equalsIgnoreCase("gif")
                || extension.equalsIgnoreCase("bmp");
    }

    private class ImageAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return imageFiles.size();
        }

        @Override
        public Object getItem(int position) {
            return imageFiles.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageView;
            if (convertView == null) {
                imageView = new ImageView(MainActivity6.this);
                imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            } else {
                imageView = (ImageView) convertView;
            }
            return imageView;

        }
    }
}