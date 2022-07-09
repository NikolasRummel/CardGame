package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.Move;
import de.aesettlingen.cardgame.logic.card.Card;

public class MauMauMove extends Move {
    private Card card = null;
    private final boolean isRaisingACard;

    public MauMauMove(String playerName, Card card) {
        super(playerName);
        this.card = card;
        this.isRaisingACard = false;
    }

    public MauMauMove(String playerName) {
        super(playerName);
        this.isRaisingACard = true;
    }

    public Card getCard() {
            return this.card;
    }

    public boolean isRaisingACard() {
        return this.isRaisingACard;
    }
}