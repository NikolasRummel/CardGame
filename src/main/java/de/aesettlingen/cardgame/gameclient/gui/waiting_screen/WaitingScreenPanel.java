package de.aesettlingen.cardgame.gameclient.gui.waiting_screen;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 * The type Waiting screen panel.
 */
public class WaitingScreenPanel extends JPanel {

    private final StartMethod startMethod;
    private ArrayList<String> players;
    private JButton startButton;

    private JList<Object> playerList;

    /**
     * Instantiates a new Waiting screen panel.
     *
     * @param startMethod the start method
     */
    public WaitingScreenPanel(StartMethod startMethod) {
        this.startMethod = startMethod;
        this.players = new ArrayList<>();

        this.initGuiElements();
    }

    /**
     * Adds the gui elements (labels, playerList...)
     */
    private void initGuiElements() {
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        JLabel waitingLabel = new JLabel("Waiting for other players...");
        waitingLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        topPanel.add(waitingLabel);

        this.add(topPanel, BorderLayout.NORTH);

        JPanel leftPanel = new JPanel();
        playerList = new JList<>();
        playerList.setPreferredSize(new Dimension(100, 250));
        playerList.setFixedCellHeight(50);
        updateDisplayOfPlayer();
        leftPanel.add(playerList);
        this.add(leftPanel, BorderLayout.WEST);


        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Verdana", Font.BOLD, 20));
        startButton.addActionListener(al -> startMethod.start());
        bottomPanel.add(startButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        updateBorder();
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public ArrayList<String> getPlayers() {
        return players;
    }

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(ArrayList<String> players) {
        this.players = players;

        updateDisplayOfPlayer();
    }

    /**
     * Add player.
     *
     * @param playerName the player name
     */
    public void addPlayer(String playerName) {
        players.add(playerName);
        updateDisplayOfPlayer();
    }

    /**
     * Remove player.
     *
     * @param playerName the player name
     */
    public void removePlayer(String playerName) {
        players.remove(playerName);
        updateDisplayOfPlayer();
    }

    private void updateDisplayOfPlayer() {
        updatePlayerList();
        updateBorder();
    }

    private void updatePlayerList() {
        playerList.setListData(players.toArray());
    }

    private void updateBorder() {
        this.playerList.setBorder(BorderFactory.createTitledBorder("Players " + getPlayers().size() + "/4"));
    }
}
