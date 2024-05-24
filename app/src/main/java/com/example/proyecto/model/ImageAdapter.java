package com.example.proyecto.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.proyecto.R;

import java.io.File;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    Medidas[] medidas;
    Context c;

    private byte[] artBytes;

    public ImageAdapter(Medidas[] Medidas, Context c) {
        this.c = c;
        this.medidas = Medidas;
    }

    @Override
    public int getCount() {
        return medidas.length;
    }

    @Override
    public Object getItem(int i) {
        return medidas[i];
    }

    @Override
    public long getItemId(int i) {
        return medidas[i].getId();
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View vista_elemento = inflater.inflate(R.layout.grid_item, viewGroup, false);

        ImageView albumArtImageView = vista_elemento.findViewById(R.id.imageView);

        // Cargar imágenes desde archivos PNG o JPG
        try {
            File imageFile = medidas[i].Video;
            if (imageFile.getName().toLowerCase().endsWith(".png") || imageFile.getName().toLowerCase().endsWith(".jpg")) {
                Bitmap bitmap = BitmapFactory.decodeFile(imageFile.getAbsolutePath());
                albumArtImageView.setImageBitmap(bitmap);
            } else {
                // Si el archivo no es PNG o JPG, puedes establecer una imagen predeterminada o dejarlo vacío
                albumArtImageView.setImageResource(R.drawable.ic_launcher_foreground); // Establecer una imagen predeterminada
                // albumArtImageView.setVisibility(View.GONE); // Ocultar ImageView si no hay imagen para mostrar
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return vista_elemento;
    }
}
