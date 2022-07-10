package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.Player;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;

import java.util.LinkedList;

/**
 * The type Mau mau player.
 */
public class MauMauPlayer extends Player {

    /**
     * Instantiates a new Mau mau player.
     *
     * @param name the name
     */
    public MauMauPlayer(String name) {
        super(name);
    }

    /**
     * Instantiates a new Mau mau player.
     *
     * @param name the name
     * @param hand the hand
     */
    public MauMauPlayer(String name, CardHand hand) {
        super(name, hand);
    }

    /**
     * Instantiates a new Mau mau player.
     *
     * @param name  the name
     * @param cards the cards
     */
    public MauMauPlayer(String name, LinkedList<Card> cards) {
        super(name, cards);
    }
}
