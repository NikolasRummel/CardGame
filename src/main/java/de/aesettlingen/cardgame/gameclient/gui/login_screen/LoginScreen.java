package de.aesettlingen.cardgame.gameclient.gui.login_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginScreen extends JFrame {

    private JButton loginButton;
    private JLabel label;
    private JTextField inputField;

    private LoginMethod loginMethod;

    public LoginScreen(LoginMethod loginMethod) {
        this.loginMethod = loginMethod;
        initGuiElements();
    }

    private void initGuiElements() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        this.label = new JLabel("Please enter your username");
        this.label.setBounds(100, 18, 200, 20);

        this.inputField = new JTextField();
        this.inputField.setBounds(100, 37, 200, 28);

        this.loginButton = new JButton("login");
        this.loginButton.setBounds(150, 110, 100, 30);
        this.loginButton.addActionListener(al -> loginMethod.login(inputField.getText()));

        panel.add(label);
        panel.add(this.inputField);
        panel.add(this.loginButton);

        this.setTitle("CardGame - Login");
        this.setLocation(new Point(500, 300));
        this.add(panel);
        this.setSize(new Dimension(400, 200));

        this.setVisible(true);
    }

}