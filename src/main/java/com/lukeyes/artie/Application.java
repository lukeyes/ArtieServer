package com.lukeyes.artie;

import javax.swing.*;

public class Application {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                new Runnable() {
                    @Override
                    public void run() {
                        ChatServer.getInstance();
                        ArtieFrame.createAndShowGUI();
                    }
                }
        );
    }
}