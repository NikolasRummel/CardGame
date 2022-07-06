package de.aesettlingen.cardgame.gameclient.gui.waiting_screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaitingScreenPanel extends JPanel {

    private StartMethod startMethod;
    private ArrayList<String> players;
    private JButton startButton;

    private JList<Object> playerList;

    public WaitingScreenPanel(StartMethod startMethod) {
        this.startMethod = startMethod;
        this.players = new ArrayList<>();

        this.initGuiElements();
    }

    private void initGuiElements() {
//        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
//        topPanel.setBorder(new LineBorder(Color.RED));
        topPanel.setLayout(new FlowLayout());
        JLabel waitingLabel = new JLabel("Waiting for other players...");
        waitingLabel.setFont(new Font("Verdana", Font.BOLD, 30));
        topPanel.add(waitingLabel);

        this.add(topPanel, BorderLayout.NORTH);


        JPanel leftPanel = new JPanel();
//        leftPanel.setBorder(new LineBorder(Color.RED));
        updatePlayerList();
        playerList.setPreferredSize(new Dimension(100, 200));
        playerList.setFixedCellHeight(50);
        leftPanel.add(playerList);
        this.add(leftPanel, BorderLayout.WEST);


        JPanel bottomPanel = new JPanel();
//        bottomPanel.setBorder(new LineBorder(Color.RED));
        bottomPanel.setLayout(new FlowLayout());
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Verdana", Font.BOLD, 20));
        startButton.addActionListener(al -> startMethod.start());
        bottomPanel.add(startButton);
        this.add(bottomPanel, BorderLayout.SOUTH);

        updateBorder();
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public void addPlayer(String playerName) {
        players.add(playerName);
        updateDisplayOfPlayer();
    }

    public void removePlayer(String playerName) {
        players.remove(playerName);
        updateDisplayOfPlayer();
    }

    private void updateDisplayOfPlayer() {
        updatePlayerList();
        updateBorder();
    }

    private void updatePlayerList() {
        playerList = new JList<>(players.toArray());
    }

    private void updateBorder() {
        this.playerList.setBorder(BorderFactory.createTitledBorder("Players " + getPlayers().size() + "/4"));
    }

}
