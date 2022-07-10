package de.aesettlingen.cardgame.logic.card;

import java.util.Collections;
import java.util.LinkedList;

/**
 * The type Card discard pile. (Ablagestapel)
 */
public class CardDiscardPile extends CardStack{

    /**
     * Instantiates a new Card discard pile.
     */
    public CardDiscardPile() {
        super();
    }

    /**
     * Instantiates a new Card discard pile.
     *
     * @param cards the cards
     */
    public CardDiscardPile(LinkedList<Card> cards) {
        super(cards);
    }

    /**
     * Instantiates a new Card discard pile.
     *
     * @param cards the cards
     */
    public CardDiscardPile(Card... cards) {
        super(cards);
    }

    /**
     * Gets shuffeled cards.
     *
     * @return the shuffeled cards
     */
    public LinkedList<Card> getShuffeledCards() {
        LinkedList<Card> shuffeled = new LinkedList<>(cards);
        Collections.shuffle(shuffeled);
        return shuffeled;
    }

    /**
     * Peak card.
     *
     * @return the card
     */
    public Card peak() {
        return cards.getLast();
    }

    /**
     * Remove all but last.
     */
    public void removeAllButLast() {
        this.setCards(this.peak());
    }
}