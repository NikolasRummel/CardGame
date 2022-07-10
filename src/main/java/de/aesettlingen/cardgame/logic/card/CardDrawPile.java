package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;

/**
 * The type Card draw pile. (Aufnahmestapel)
 */
public class CardDrawPile extends CardStack {

    /**
     * Instantiates a new Card draw pile.
     */
    public CardDrawPile() {
        super();
    }

    /**
     * Instantiates a new Card draw pile.
     *
     * @param cards the cards
     */
    public CardDrawPile(LinkedList<Card> cards) {
        super(cards);
    }

    /**
     * Instantiates a new Card draw pile.
     *
     * @param cards the cards
     */
    public CardDrawPile(Card... cards) {
        super(cards);
    }
}
