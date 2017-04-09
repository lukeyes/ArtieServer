package com.lukeyes.artie.controller;

import com.lukeyes.artie.Controller;
import com.lukeyes.artie.Data;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendController implements ActionListener, DocumentListener{

    @Override
    public void actionPerformed(ActionEvent e) {

        String puppetText = Data.getInstance().getPuppetText();
        Controller.getInstance().send(puppetText);
    }

    private String getDocumentString(DocumentEvent e) throws BadLocationException {
        final Document document = e.getDocument();
        final int length = document.getLength();
        return document.getText(0,length);
    }

    void updatePuppetTextData(DocumentEvent e) {
        try {
            String text = getDocumentString(e);
            Data.getInstance().setPuppetText(text);
        } catch (BadLocationException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        updatePuppetTextData(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updatePuppetTextData(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updatePuppetTextData(e);
    }
}
