package com.lukeyes.artie.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Favorites {

    private Map<String, String> favorites;

    public Favorites() {
    }

    public Map<String, String> getFavorites() {
        return favorites;
    }

    public void setFavorites(Map<String, String> favorites) {
        this.favorites = favorites;
    }

    public static Favorites create(String fileName) {
        Favorites favorites = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            // load in json

            ObjectMapper mapper = new ObjectMapper();
            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = reader.readLine()) != null) {
                builder.append(aux);
            }

            String text = builder.toString();
            reader.close();

            favorites = mapper.readValue(text, Favorites.class);

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return favorites;
    }
}
