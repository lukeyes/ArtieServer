package com.lukeyes.artie.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Script {

    public String title;
    public List<String> lines;

    public static Script create(String fileName) {

        Script script = null;
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

            script = mapper.readValue(text, Script.class);

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return script;
    }
}
