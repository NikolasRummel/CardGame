package de.aesettlingen.cardgame.gameclient.gui.login_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JPanel {

    private JButton loginButton;
    private JLabel textForButton = new JLabel("login name");
    private JTextField inputField = new JTextField();

    private LoginMethod loginMethod;

    public LoginScreen(LoginMethod loginMethod) {
        this.loginMethod = loginMethod;
        initGuiElements();
    }

    private void initGuiElements() {
        loginButton = new JButton("login");
        loginButton.addActionListener(al -> loginMethod.login(inputField.getText()));

        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) loginMethod.login(inputField.getText());
            }
        });

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(textForButton, LEFT_ALIGNMENT);
        panel.add(loginButton);

        this.setLayout(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        this.add(loginButton, BorderLayout.SOUTH);
    }
}