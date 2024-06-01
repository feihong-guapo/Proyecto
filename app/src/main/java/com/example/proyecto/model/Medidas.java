package com.example.proyecto.model;

import java.io.File;
import java.io.Serializable;

public class Medidas implements Serializable {
    String Nombre;
    File Video;
    long Id;

    // Constructor
    public Medidas(long Id, String Nombre, File video) {
        this.Id=Id;
        this.Nombre= Nombre;
        this.Video= video;
    }
    public String getNombre() {
        return Nombre;
    }
    public File getVideo() {
        return Video;
    }
    public long getId() {
        return Id;
    }


}