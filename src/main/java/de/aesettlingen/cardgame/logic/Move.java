package de.aesettlingen.cardgame.logic;

/**
 * The type Move.
 */
public class Move {

    private final String playerName;

    /**
     * Instantiates a new Move.
     *
     * @param PlayerName the player name
     */
    public Move(String PlayerName) {
        this.playerName = PlayerName;
    }

    /**
     * Gets player name.
     *
     * @return the player name
     */
    public String getPlayerName() {
        return this.playerName;
    }
}
