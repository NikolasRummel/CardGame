package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.Move;
import de.aesettlingen.cardgame.logic.card.Card;

public class MauMauMove extends Move {
    private final Card card;

    public MauMauMove(String playerName, Card card) {
        super(playerName);
        this.card = card;
    }

    public Card getCard() {
            return this.card;
    }
}