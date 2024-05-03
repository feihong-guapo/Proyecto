package com.example.proyecto.model;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Incidence implements Serializable {
    private int user_id;
    private String incidence;
    private String categoria;
    private String extra;

    private String date;

    public Incidence(){

    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getIncidence() {
        return incidence;
    }

    public void setIncidence(String incidence) {
        this.incidence = incidence;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getJsonParams() throws JSONException {
        JSONObject json = new JSONObject();
        json.put("user_id", this.user_id);
        json.put("fecha", this.date);
        json.put("categoria",this.categoria);
        json.put("mensaje", this.incidence);
        json.put("extra", this.extra);

        return json.toString();


    }
}
