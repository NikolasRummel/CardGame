package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.Move;
import de.aesettlingen.cardgame.logic.card.Card;

/**
 * The type Mau mau move.
 */
public class MauMauMove extends Move {
    private final Card card;
    private final boolean isRaisingACard;

    /**
     * Instantiates a new Mau mau move.
     *
     * @param playerName the player name
     * @param card       the card
     */
    public MauMauMove(String playerName, Card card) {
        super(playerName);
        this.card = card;
        this.isRaisingACard = card == null;
    }

    /**
     * Instantiates a new Mau mau move.
     *
     * @param playerName the player name
     */
    public MauMauMove(String playerName) {
        super(playerName);
        this.card = null;
        this.isRaisingACard = true;
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
            return this.card;
    }

    /**
     * Is raising a card boolean.
     *
     * @return the boolean
     */
    public boolean isRaisingACard() {
        return this.isRaisingACard;
    }
}