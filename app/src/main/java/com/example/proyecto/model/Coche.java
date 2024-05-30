package com.example.proyecto.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Optional;
//import com.example.proyecto.model.Coche;
import com.example.proyecto.model.Motor;

public class Coche implements Serializable {

    private int id_coche;
    private String modelo;
    private String marca;
    private Motor motor;
    private int idTamano;
    private String tipoCoche;
    private int plazas;
    private int puertas;
    private String traccion;
    private String enchufable;
    private double anchoCm;
    private double longitudCm;
    private double maleteroL;
    private double precioEuros;

    private boolean liked;

    private String imgs_src;

    public String getImgs_src() {
        return imgs_src;
    }

    public void setImgs_src(String imgs_src) {
        this.imgs_src = imgs_src;
    }

    public int getId_coche() {
        return id_coche;
    }

    public void setId_coche(int id_coche) {
        this.id_coche = id_coche;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Motor getMotor() {
        return motor;
    }

//    public void setMotor(Motor motor) {
//        this.motor = motor;
//    }

    public int getIdTamano() {
        return idTamano;
    }

    public void setIdTamano(int idTamano) {
        this.idTamano = idTamano;
    }

    public String getTipoCoche() {
        return tipoCoche;
    }

    public void setTipoCoche(String tipoCoche) {
        this.tipoCoche = tipoCoche;
    }

    public int getPlazas() {
        return plazas;
    }

    public void setPlazas(int plazas) {
        this.plazas = plazas;
    }

    public int getPuertas() {
        return puertas;
    }

    public void setPuertas(int puertas) {
        this.puertas = puertas;
    }

    public String getTraccion() {
        return traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }

    public String getEnchufable() {
        return enchufable;
    }

    public void setEnchufable(String enchufable) {
        this.enchufable = enchufable;
    }

    public double getAnchoCm() {
        return anchoCm;
    }

    public void setAnchoCm(double anchoCm) {
        this.anchoCm = anchoCm;
    }

    public double getLongitudCm() {
        return longitudCm;
    }

    public void setLongitudCm(double longitudCm) {
        this.longitudCm = longitudCm;
    }

    public double getMaleteroL() {
        return maleteroL;
    }

    public void setMaleteroL(double maleteroL) {
        this.maleteroL = maleteroL;
    }

    public double getPrecioEuros() {
        return precioEuros;
    }

    public void setPrecioEuros(double precioEuros) {
        this.precioEuros = precioEuros;
    }


    public void setDataJson(JSONObject json) throws JSONException {

        this.id_coche = json.optInt("id_coche");
        this.modelo = json.optString("modelo", null);
        this.marca = json.optString("nombre_marca", null);
        this.tipoCoche = json.optString("tipo_coche", null);
        this.plazas = json.optInt("plazas");
        this.puertas = json.optInt("puertas");
        this.traccion = json.optString("traccion", null);
        this.enchufable = json.optString("enchufable", null);
        this.anchoCm = json.optDouble("ancho_cm");
        this.longitudCm = json.optDouble("longitud_cm");
        this.maleteroL = json.optDouble("maletero_l");
        this.precioEuros = json.optDouble("precio_euros");
        this.imgs_src = json.optString("car_images", null);


        if( json.getInt("es_favorito")== 1){
            this.liked = true;

        }
        else{
            this.liked = false;
        }
        this.motor = new Motor();
        this.motor.setData(json.getJSONObject("motor"));

    }
}
