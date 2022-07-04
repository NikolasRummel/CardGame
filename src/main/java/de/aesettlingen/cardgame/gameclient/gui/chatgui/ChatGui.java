package de.aesettlingen.cardgame.gameclient.gui.chatgui;

import de.aesettlingen.cardgame.gameclient.CardGameClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ChatGui extends JPanel {

    // width = 270

    private final JTextArea textDisplay = new JTextArea();
    private final JButton sendButton = new JButton("send");
    private final JTextField inputField = new JTextField();
    public ChatGui() {
        initGuiElements();
    }

    private void initGuiElements() {

        super.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(270, 720));

        this.textDisplay.setEnabled(false);
        super.add(new JScrollPane(textDisplay), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(inputField, BorderLayout.CENTER);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send();
                }
            }
        });

        bottomPanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(al -> send());

        super.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void send() {
//        CardGameClient.getInstance().sendMessage(inputField.getText());
        inputField.setText("");
    }

    public static void main(String[] args) {
        JFrame f = new JFrame();
        ChatGui c = new ChatGui();
        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(c, BorderLayout.EAST);
        p.add(new JPanel());
        f.add(p);
        f.setSize(new Dimension(1080, 720));
        f.setVisible(true);
    }
}