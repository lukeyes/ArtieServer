package com.lukeyes.artie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukeyes.artie.controller.*;
import com.lukeyes.artie.domain.Message;

public class Controller {

    private static Controller instance;

    public static Controller getInstance() {
        if( instance == null) {
            instance = new Controller();
        }

        return instance;
    }

    FavoritesController favoritesController;
    SendController puppetController;
    ScriptListController scriptListController;
    ScriptContentController scriptContentController;
    AddTextController addTextController;
    ObjectMapper mapper;

    private Controller() {
        favoritesController = new FavoritesController();
        puppetController = new SendController();
        scriptListController = new ScriptListController();
        scriptContentController = new ScriptContentController();
        addTextController = new AddTextController();
        mapper = new ObjectMapper();
    }

    public void send(String text) {
        System.out.println(text);

        Message message = new Message();
        message.sender = "server";
        message.recipient = "client";
        message.message = text;

        try {
            final String messageAsText = mapper.writeValueAsString(message);
            ChatServer.getInstance().sendToAll(messageAsText);
            Data.getInstance().historyModel.addElement(messageAsText);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public SendController getPuppetController() {
        return puppetController;
    }

    public AddTextController getAddTextController() {
        return addTextController;
    }
}
