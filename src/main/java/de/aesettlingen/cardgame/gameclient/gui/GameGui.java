package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginScreen;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.StartMethod;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.WaitingScreenPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class GameGui extends JFrame {

    private WaitingScreenPanel waitingScreenPanel;

    public GameGui() {
        this.waitingScreenPanel = new WaitingScreenPanel(new StartMethod() {
            @Override
            public void start() {
                System.out.println("start game");
            }
        });

        this.setTitle("CardGame - Waiting");
        this.setLocation(new Point(500, 300));
        this.add(this.waitingScreenPanel);
        this.setSize(new Dimension(1280, 720));

        this.setVisible(true);
    }

    public WaitingScreenPanel getWaitingScreenPanel() {
        return waitingScreenPanel;
    }
}