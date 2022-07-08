package de.aesettlingen.cardgame.logic;

public class Move {

    private final String playerName;

    public Move(String PlayerName) {
        this.playerName = PlayerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }
}
