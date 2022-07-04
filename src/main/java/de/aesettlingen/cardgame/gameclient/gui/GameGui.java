package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.gameclient.gui.chatgui.ChatGui;
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
    private ChatGui chatGui;
    //ChatPanel

    public GameGui() {
        this.waitingScreenPanel = new WaitingScreenPanel(new StartMethod() {
            @Override
            public void start() {
                System.out.println("start game");
            }
        });

        this.setTitle("CardGame - Waiting");
        //this.setLocation(new Point(500, 300));
        //this.add(this.waitingScreenPanel);
        this.setSize(new Dimension(1280, 720));
        this.setResizable(false);
        this.setVisible(true);

        addChatPanel();
    }

    private void addChatPanel() {
        this.chatGui = new ChatGui();

        for (int i = 0; i < 20; i++)
            chatGui.onReceiveMessage(new MessagePacket("Simon", "hi"));

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(chatGui, BorderLayout.EAST);
        panel.add(new JPanel());

        this.add(panel);
    }

    public WaitingScreenPanel getWaitingScreenPanel() {
        return waitingScreenPanel;
    }

    public ChatGui getChatGui() {
        return chatGui;
    }
}