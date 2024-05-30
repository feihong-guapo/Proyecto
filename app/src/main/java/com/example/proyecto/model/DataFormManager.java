package com.example.proyecto.model;

import java.util.HashMap;

public class DataFormManager {
    private static DataFormManager instance;
    private HashMap<String, String> data = new HashMap<>();
    private User currentUser;  // Referencia al usuario

    private DataFormManager() {}

    public static synchronized DataFormManager getInstance() {
        if (instance == null){
            instance = new DataFormManager();
        }
        return instance;
    }

    public void setUser(User user) {
        this.currentUser = user;
    }

    public User getUser() {
        return currentUser;
    }

    public void saveData(String key, String value){
        data.put(key, value);
    }

    public void saveIntData(String key, int value){
        data.put(key, String.valueOf(value));
    }

    public int getIntData(String key) {
        try {
            return Integer.parseInt(data.get(key));
        } catch (NumberFormatException e) {
            return -1; // or any other default value
        }
    }

    public String getData(String key) {
        return data.get(key);
    }

    public HashMap<String, String> getAllData() {
        return data;
    }

    public void clearData(){
        data.clear();
    }
}
