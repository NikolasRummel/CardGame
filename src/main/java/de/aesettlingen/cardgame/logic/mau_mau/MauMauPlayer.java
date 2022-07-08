package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.Player;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;

import java.util.LinkedList;

public class MauMauPlayer extends Player {

    public MauMauPlayer(String name) {
        super(name);
    }

    public MauMauPlayer(String name, CardHand hand) {
        super(name, hand);
    }

    public MauMauPlayer(String name, LinkedList<Card> cards) {
        super(name, cards);
    }
}
