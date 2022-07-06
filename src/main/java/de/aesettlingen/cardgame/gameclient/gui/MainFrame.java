package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.gameclient.gui.chatgui.ChatPanel;
import de.aesettlingen.cardgame.gameclient.gui.waiting_screen.WaitingScreenPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MainFrame extends JFrame {

    private WaitingScreenPanel waitingScreenPanel;
    private ChatPanel chatPanel;
    private GamePanel gameGui;
    private JPanel mainPanel;

    public MainFrame() {
        initGuiElements();
    }

    private void initGuiElements() {
        this.waitingScreenPanel = new WaitingScreenPanel(() -> System.out.println("start game"));

        this.chatPanel = new ChatPanel();
        this.gameGui = new GamePanel();

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new BorderLayout());

        this.setContentOfMainFrame(waitingScreenPanel);

        super.setContentPane(mainPanel);
        super.setTitle("CardGame - Waiting");
        super.setSize(new Dimension(1280, 720));
        super.setResizable(false);

        this.setVisible(true);
    }

    public void switchToGame() {
        setContentOfMainFrame(gameGui);
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

}