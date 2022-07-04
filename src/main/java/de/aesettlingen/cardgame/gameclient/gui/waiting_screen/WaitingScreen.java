package de.aesettlingen.cardgame.gameclient.gui.waiting_screen;

import javax.swing.*;

public class WaitingScreen {

    private StartMethod startMethod;
    private JButton startButton = new JButton("start");

    WaitingScreen(StartMethod startMethod) {
        this.startMethod = startMethod;
    }

    private void initGuiElements() {
        startButton.addActionListener(al -> startMethod.start());
    }
}
