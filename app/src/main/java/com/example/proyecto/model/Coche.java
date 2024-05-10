package com.example.proyecto.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Coche implements Serializable {
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

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

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
        this.modelo=json.getString("modelo");
        this.marca= json.getString("nombre_marca");
        this.tipoCoche=  json.getString("tipo_coche");
        this.plazas=  json.getInt("plazas");
        this.puertas = json.getInt("puertas");
        this.traccion = json.getString("traccion");
        this.enchufable=json.getString("enchufable");
        this.anchoCm=json.getDouble("ancho_cm");
        this.longitudCm= json.getDouble("longitud_cm");
        this.maleteroL= json.getDouble("maletero_l");
        this.precioEuros = json.getDouble("precio_euros");
    }
}
