package com.lukeyes.artie.controller;

import com.lukeyes.artie.Controller;
import com.lukeyes.artie.Data;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScriptContentController implements ListSelectionListener, ActionListener {
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if(!e.getValueIsAdjusting()) {

            ListSelectionModel listSelectionModel = (ListSelectionModel) e.getSource();

            int firstIndex = listSelectionModel.getMinSelectionIndex();
            System.out.println("Index: " + firstIndex);

            if(firstIndex >= 0) {
                String currentLine = Data.getInstance().getScriptContentModel().getElementAt(firstIndex);
                System.out.println("Current Line - " + currentLine);
                Data.getInstance().setScriptContentText(currentLine);
            } else {
                Data.getInstance().setScriptContentText(null);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String scriptContextText = Data.getInstance().getScriptContentText();
        if(scriptContextText != null) {
            Controller.getInstance().send(scriptContextText);
        }
    }
}
