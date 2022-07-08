package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.gameclient.gui.chatgui.ChatPanel;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.GamePanel;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.StartMethod;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.WaitingScreenPanel;

import javax.swing.*;
import java.awt.*;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MainFrame extends ColoredFrame {

    private WaitingScreenPanel waitingScreenPanel;
    private ChatPanel chatPanel;
    private GamePanel gamePanel;
    private JPanel mainPanel;

    public MainFrame() {
        initGuiElements();
    }

    private void initGuiElements() {
        this.waitingScreenPanel = new WaitingScreenPanel(new StartMethod() {
            @Override
            public void start() {
                System.out.println("start game");
            }
        });

        this.chatPanel = new ChatPanel();
        this.gamePanel = new GamePanel();

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());

        setContentOfMainFrame(waitingScreenPanel);


        super.setContentPane(mainPanel);
        super.setTitle("CardGame - Waiting");
        //this.setLocation(new Point(500, 300));
        //this.add(this.waitingScreenPanel);
        super.setSize(new Dimension(1280, 720));
        super.setResizable(false);
//        super.setVisible(true);

        this.setVisible(true);
    }

    public void switchToGame() {
        setContentOfMainFrame(gamePanel);
    }

    public void switchToWaitingScreen() {
        setContentOfMainFrame(waitingScreenPanel);
    }

    private void setContentOfMainFrame(JComponent component) {

        this.mainPanel.removeAll();
        this.mainPanel.add(chatPanel, BorderLayout.EAST);
        this.mainPanel.add(component, BorderLayout.CENTER);
    }

    public WaitingScreenPanel getWaitingScreenPanel() {
        return waitingScreenPanel;
    }

    public ChatPanel getChatPanel() {
        return chatPanel;
    }

    public static void main(String[] args) {

        MainFrame mainFrame = new MainFrame();
        mainFrame.switchToGame();
    }
}