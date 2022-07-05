package de.aesettlingen.cardgame.gameclient.gui;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    public GamePanel() {

        // some stuff so you can see the game gui
        this.add(new JLabel("this is the game gui"));
        this.setBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(Color.RED),
                        "game gui"
                )
        );
    }
}