package de.aesettlingen.cardgame.gameclient.gui.waiting_screen;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class WaitingScreenPanel extends JPanel {

    private StartMethod startMethod;
    private ArrayList<String> players;
    private JButton startButton = new JButton("start");
    private JList playerList;

    public WaitingScreenPanel(StartMethod startMethod) {
        this.startMethod = startMethod;
        this.players = new ArrayList<>();

        this.initGuiElements();
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    private void initGuiElements() {
        startButton.addActionListener(al -> startMethod.start());

        playerList = new JList(players.toArray());
        playerList.setLocation(0, 0);
        playerList.setPreferredSize(new Dimension(100, 200));
        //playerList.setFont(new Font("Dialog", Font.PLAIN, 30));
        playerList.setFixedCellHeight(50);

        this.add(playerList);
        this.setBorder(BorderFactory.createTitledBorder("Players " + getPlayers().size() + "/4"));
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }
}
