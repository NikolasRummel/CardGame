package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;

/**
 * The type Card collection.
 */
abstract public class CardCollection {

    /**
     * The Cards.
     */
    protected LinkedList<Card> cards;

    /**
     * Instantiates a new Card collection.
     */
    public CardCollection() {
        this.cards = new LinkedList<>();
    }

    /**
     * Instantiates a new Card collection.
     *
     * @param cards the cards
     */
    public CardCollection(LinkedList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Instantiates a new Card collection.
     *
     * @param cards the cards
     */
    public CardCollection(Card... cards) {
        this.cards = new LinkedList<>();
        for (Card card : cards) {
            this.cards.addLast(card);
        }
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public LinkedList<Card> getCards() {
        return new LinkedList<>(cards);
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(Card ... cards) {
        this.cards = new LinkedList<>();

        for (Card card : cards) {
            this.cards.addLast(card);
        }
    }

    /**
     * Size int.
     *
     * @return the int
     */
    public int size() {
        return cards.size();
    }

    /**
     * Is empty boolean.
     *
     * @return the boolean
     */
    public boolean isEmpty() {
        return cards.size() == 0;
    }

    /**
     * Is not empty boolean.
     *
     * @return the boolean
     */
    public boolean isNotEmpty() {
        return cards.size() != 0;
    }

    /**
     * Clear.
     */
    public void clear() {
        cards.clear();
    }
}