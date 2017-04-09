package com.lukeyes.artie;

import com.lukeyes.artie.domain.Favorites;
import com.lukeyes.artie.domain.Script;
import com.lukeyes.artie.domain.ScriptList;

import javax.swing.*;
import java.io.File;
import java.util.*;

public class Data {

    static Data instance = null;

    public static Data getInstance() {
        if(instance == null) {
            instance = new Data();
        }

        return instance;
    }

    ScriptList masterList;
    Map<String, Script> scripts;
    Favorites favorites;
    String puppetText;
    String scriptContentText;
    List<String> emotionList;

    DefaultListModel<String> historyModel;
    DefaultListModel<String> scriptModel;
    DefaultListModel<String> scriptContentModel;

    public DefaultListModel<String> getScriptModel() {
        return scriptModel;
    }

    public DefaultListModel<String> getScriptContentModel() {
        return scriptContentModel;
    }

    public Map<String, Script> getScripts() {
        return scripts;
    }

    public Favorites getFavorites() {
        return favorites;
    }

    public String getPuppetText() {
        return puppetText;
    }

    public void setPuppetText(String puppetText) {
        this.puppetText = puppetText;
    }

    public String getScriptContentText() {
        return scriptContentText;
    }

    public void setScriptContentText(String scriptContentText) {
        this.scriptContentText = scriptContentText;
    }

    public String[] getEmotionList() { return  new String[] {"happy","angry","bedroom","begging","buckteeth","dead","disgust","dizzy","eyeroll","heart","laughter","mischevious","money","neutral","peeved","sad","stars","stoned","surprised","thinking","worry"};}

    private Data() {

        scriptModel = new DefaultListModel<>();
        scriptContentModel = new DefaultListModel<>();
        favorites = Favorites.create("Favorites.json");
        historyModel = new DefaultListModel<>();
        emotionList = new ArrayList<String>();

        loadScriptList("Scripts.json");
       // loadEmotionList("Faces.json");
    }

    private void loadScriptFromMasterList(String parentPath) {
        List<String> titleList2 = masterList.getScripts();

        scripts = new HashMap<>();
        for(String title : titleList2) {
            java.nio.file.Path path = java.nio.file.Paths.get(parentPath, title);
            String absolutePath = path.toString();
            final Script script = Script.create(absolutePath);
            scripts.put(script.title, script);
        }

        Set<String> titles = scripts.keySet();
        scriptModel.clear();
        titles.forEach(scriptModel::addElement);

        Script currentScript = scripts.get(titles.toArray()[0]);
        scriptContentModel.clear();
        currentScript.lines.forEach(scriptContentModel::addElement);
    }

    public void loadScriptList(String fileName) {
        masterList = ScriptList.create(fileName);
        loadScriptFromMasterList("");
    }

    public void loadScriptList(File fileToOpen) {
        masterList = ScriptList.open(fileToOpen);
        String parentPath = fileToOpen.getParentFile().getAbsolutePath();
        System.out.println("Parent path: " + parentPath);
        loadScriptFromMasterList(parentPath);
    }

    public void loadEmotionList(String fileName) {
       // masterList = ScriptList.create(fileName);
       // emotionList = new String[] {"happy","angry","bedroom","begging","buckteeth","dead","disgust","dizzy","eyeroll","heart","laughter","mischevious","money","neutral","peeved","sad","stars","stoned","surprised","thinking","worry"};
    }

    public void loadEmotionList(File fileToOpen) {
        masterList = ScriptList.open(fileToOpen);
        emotionList = masterList.getScripts();
    }

    public DefaultListModel<String> getHistoryModel() {
        return historyModel;
    }
}
