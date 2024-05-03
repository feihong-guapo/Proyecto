package com.example.proyecto.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class User implements Serializable {
    private int user_id;
    private String password;
    private String nombre;
    private String apellidos;
    private String email;
    private String account_type;
    private int verified;
    private int edad;
    private String telefono;
    private String ciudad;
    private String direccion;
    private String username;

    public User(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public int getVerified() {
        return verified;
    }

    public void setVerified(int verified) {
        this.verified = verified;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getjSonparamsReg() throws JSONException {
        JSONObject jsonParams = new JSONObject();
        jsonParams.put("password",this.password);
        jsonParams.put("nombre",this.nombre);
        jsonParams.put("apellidos", this.apellidos);
        jsonParams.put("email", this.email);
        jsonParams.put("account_type", "base");
        jsonParams.put("verified", 0);
        jsonParams.put("edad", this.edad);
        jsonParams.put("telefono", this.telefono);
        jsonParams.put("ciudad", this.ciudad);
        jsonParams.put("direccion", this.direccion);
        jsonParams.put("username", this.username);

        return jsonParams.toString();

    }
    public void setUserData(JSONObject json) throws JSONException {

        this.nombre = json.getString("nombre");
        this.apellidos= json.getString("apellidos");
        this.user_id = json.getInt("user_id");
        this.email = json.getString("email");
        this.edad = json.getInt("edad");
        this.telefono = json.getString("telefono");
        this.ciudad = json.getString("ciudad");
        this.direccion = json.getString("direccion");
        this.username = json.getString("username");
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", email='" + email + '\'' +
                ", account_type='" + account_type + '\'' +
                ", verified=" + verified +
                ", edad=" + edad +
                ", telefono='" + telefono + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", direccion='" + direccion + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
