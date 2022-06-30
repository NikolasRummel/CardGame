package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginScreen;

import javax.swing.*;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class GameGui extends JFrame {

    private LoginScreen loginScree = new LoginScreen(lm -> {
        // TODO: login
    });
}