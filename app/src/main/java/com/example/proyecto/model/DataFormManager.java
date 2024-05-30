package com.example.proyecto.model;

import java.io.Serializable;
import java.util.HashMap;



    public class DataFormManager<T extends Serializable> {
        private static DataFormManager<Serializable> instance;
        private HashMap<String, String> data = new HashMap<>();


        DataFormManager(){}

        public static DataFormManager getInstance() {
            if (instance == null){
                instance = new DataFormManager();
            }
            return instance;
        }

        public void saveData(String key, String value){
            data.put(key, value);
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

