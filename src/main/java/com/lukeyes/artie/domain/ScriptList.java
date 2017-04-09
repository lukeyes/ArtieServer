package com.lukeyes.artie.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class ScriptList {

    private List<String>  scripts;

    public ScriptList() {
    }

    public List<String> getScripts() {

        return scripts;
    }

    public void setScripts(List<String> scripts) {
        this.scripts = scripts;
    }

    public static ScriptList create(String fileName) {

        ScriptList scriptList = null;
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

            scriptList = mapper.readValue(text, ScriptList.class);

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return scriptList;
    }

    public static ScriptList open(File fileToOpen) {

        ScriptList scriptList = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileToOpen.getAbsolutePath()));

            // load in json

            ObjectMapper mapper = new ObjectMapper();
            StringBuilder builder = new StringBuilder();
            String aux = "";

            while ((aux = reader.readLine()) != null) {
                builder.append(aux);
            }

            String text = builder.toString();
            reader.close();

            scriptList = mapper.readValue(text, ScriptList.class);

        } catch (IOException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        return scriptList;
    }
}
