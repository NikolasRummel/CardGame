package de.aesettlingen.cardgame.gameclient.gui.chatgui;

import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.gameclient.CardGameClient;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatGui extends JPanel {

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
        this.textDisplay.setDisabledTextColor(Color.BLACK);

        super.add(new JScrollPane(textDisplay), BorderLayout.CENTER);
        Border b = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.BLACK), "Group Chat");
        this.setBorder(b);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        bottomPanel.add(inputField, BorderLayout.CENTER);
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    onSend();
            }
        });

        bottomPanel.add(sendButton, BorderLayout.EAST);
        sendButton.addActionListener(al -> onSend());

        super.add(bottomPanel, BorderLayout.SOUTH);
    }

    private void onSend() {
        CardGameClient.getInstance().sendMessage(inputField.getText());
        inputField.setText("");
    }

    public void onReceiveMessage(MessagePacket m) {
        String time = new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date(m.getTimestamp()));

        textDisplay.setText(
                textDisplay.getText() + String.format("[%s] %s\n%s\n\n", time, m.getSender(), m.getMessage())
        );
    }

    public static void main(String[] args) { // test chat gui
        JFrame f = new JFrame();
        ChatGui c = new ChatGui();

        for (int i = 0; i < 20; i++)
            c.onReceiveMessage(new MessagePacket("Simon", "hi"));

        JPanel p = new JPanel();
        p.setLayout(new BorderLayout());
        p.add(c, BorderLayout.EAST);
        p.add(new JPanel());
        f.add(p);
        f.setSize(new Dimension(1080, 720));
        f.setResizable(false);
        f.setVisible(true);
    }
}