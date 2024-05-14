package com.example.proyecto.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Motor implements Serializable {
    private String tipo;
    private String transmision1;
    private String transmision2;
    private String transmision3;
    private String combustible;
    private String consumo;
    private double consumoMixtoMinL;
    private double consumoMixtoMaxL;
    private double consumoElectricoMinKw;
    private double consumoElectricoMaxKw;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTransmision1() {
        return transmision1;
    }

    public void setTransmision1(String transmision1) {
        this.transmision1 = transmision1;
    }

    public String getTransmision2() {
        return transmision2;
    }

    public void setTransmision2(String transmision2) {
        this.transmision2 = transmision2;
    }

    public String getTransmision3() {
        return transmision3;
    }

    public void setTransmision3(String transmision3) {
        this.transmision3 = transmision3;
    }

    public String getCombustible() {
        return combustible;
    }

    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }

    public String getConsumo() {
        return consumo;
    }

    public void setConsumo(String consumo) {
        this.consumo = consumo;
    }

    public double getConsumoMixtoMinL() {
        return consumoMixtoMinL;
    }

    public void setConsumoMixtoMinL(double consumoMixtoMinL) {
        this.consumoMixtoMinL = consumoMixtoMinL;
    }

    public double getConsumoMixtoMaxL() {
        return consumoMixtoMaxL;
    }

    public void setConsumoMixtoMaxL(double consumoMixtoMaxL) {
        this.consumoMixtoMaxL = consumoMixtoMaxL;
    }

    public double getConsumoElectricoMinKw() {
        return consumoElectricoMinKw;
    }

    public void setConsumoElectricoMinKw(double consumoElectricoMinKw) {
        this.consumoElectricoMinKw = consumoElectricoMinKw;
    }

    public double getConsumoElectricoMaxKw() {
        return consumoElectricoMaxKw;
    }

    public void setConsumoElectricoMaxKw(double consumoElectricoMaxKw) {
        this.consumoElectricoMaxKw = consumoElectricoMaxKw;
    }
    public void setData(JSONObject motorJson) throws JSONException {

            this.tipo = motorJson.optString("tipo", null);
            this.transmision1 = motorJson.optString("transmision1", null);
            this.transmision2 = motorJson.optString("transmision2", null);
            this.transmision3 = motorJson.optString("transmision3", null);
            this.combustible = motorJson.optString("combustible", null);
            this.consumo = motorJson.optString("consumo", null);
            this.consumoMixtoMinL = motorJson.optDouble("consumo_mixto_min_l");
            this.consumoMixtoMaxL = motorJson.optDouble("consumo_mixto_max_l");
            this.consumoElectricoMinKw = motorJson.optDouble("consumo_electrico_min_kw");
            this.consumoElectricoMaxKw = motorJson.optDouble("consumo_electrico_max_kw");
        }

    }


