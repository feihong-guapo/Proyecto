package com.example.proyecto.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class ObjectFormManager <T extends Serializable> {
    private static class Holder<T extends Serializable> {
        private static final ObjectFormManager<Serializable> INSTANCE = new ObjectFormManager<>();
    }

    private HashMap<String, T> data = new HashMap<>();



    public static <T extends Serializable> ObjectFormManager<Serializable> getInstance() {
        return Holder.INSTANCE;
    }


    public void saveData(String key, T value) {
        try (FileOutputStream fos = new FileOutputStream("data/" + key + ".ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(value);
            data.put(key, value);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public T getData(String key) {
        return data.get(key);
    }

    public HashMap<String, T> getAllData() {
        return data;
    }

    public void clearData(){
        data.clear();
    }
}