package com.example.proyecto.model;

import org.json.JSONException;
import org.json.JSONObject;

public class UserFormManager {
    // Ensure DataFormManager is correctly implemented as singleton before using

    public static User createUserFromJson(JSONObject json) throws JSONException {
        DataFormManager manager = DataFormManager.getInstance();
        User user = new User();

        // Populate the user object with data from the JSON
        populateUserFields(json, user);

        // Save the data to DataFormManager for further usage across the app
        saveUserDataToManager(manager, user);

        // Return the fully initialized User object
        return user;
    }

    private static void populateUserFields(JSONObject json, User user) throws JSONException {
        user.setNombre(json.optString("nombre", "Default Name"));
        user.setApellidos(json.optString("apellidos", "Default Surname"));
        user.setUser_id(json.optInt("user_id", -1));
        user.setEmail(json.optString("email", "no-email@example.com"));
        user.setEdad(json.optInt("edad", 0));
        user.setTelefono(json.optString("telefono", "N/A"));
        user.setCiudad(json.optString("ciudad", "N/A"));
        user.setDireccion(json.optString("direccion", "N/A"));
        user.setUsername(json.optString("username", "N/A"));
        user.setRutaImg(json.optString("prof_route", "default_route"));
    }

    private static void saveUserDataToManager(DataFormManager manager, User user) {
        manager.saveData("nombre", user.getNombre());
        manager.saveData("apellidos", user.getApellidos());
        manager.saveIntData("user_id", user.getUser_id());
        manager.saveData("email", user.getEmail());
        manager.saveIntData("edad", user.getEdad());
        manager.saveData("telefono", user.getTelefono());
        manager.saveData("ciudad", user.getCiudad());
        manager.saveData("direccion", user.getDireccion());
        manager.saveData("username", user.getUsername());
        manager.saveData("rutaImg", user.getRutaImg());
    }
}
