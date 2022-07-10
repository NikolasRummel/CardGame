package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;


/**
 * The type Card stack.
 * A Generich stack of cards
 */
public class CardStack extends CardCollection {

    /**
     * Instantiates a new Card stack.
     */
    public CardStack() {
        super();
    }

    /**
     * Instantiates a new Card stack.
     *
     * @param cards the cards
     */
    public CardStack(LinkedList<Card> cards) {
        super(cards);
    }

    /**
     * Instantiates a new Card stack.
     *
     * @param cards the cards
     */
    public CardStack(Card... cards) {
        super(cards);
    }

    // functions

    /**
     * Push.
     *
     * @param card the card
     */
    public void push(Card card) {
        super.cards.add(card);
    }

    /**
     * Pull card.
     *
     * @return the card
     */
    public Card pull() {
       return super.cards.removeLast();
    }

    /**
     * Pull linked list.
     *
     * @param number the number
     * @return the linked list
     */
    public LinkedList<Card> pull(int number) {
        int maxIndex = super.cards.size()-1;
        return (LinkedList<Card>) super.cards.subList(maxIndex-number, maxIndex);
    }

    /**
     * Push.
     *
     * @param cards the cards
     */
    public void push(LinkedList<Card> cards) {
        super.cards.addAll(cards);
    }

    /**
     * Push to end.
     *
     * @param card the card
     */
    public void pushToEnd(Card card) {
        super.cards.addFirst(card);
    }

    /**
     * Pull from end card.
     *
     * @return the card
     */
    public Card pullFromEnd() {
        return super.cards.removeFirst();
    }

    /**
     * Pull from end linked list.
     *
     * @param number the number
     * @return the linked list
     */
    public LinkedList<Card> pullFromEnd(int number) {
        int maxIndex = super.cards.size()-1;
        return (LinkedList<Card>) super.cards.subList(0, maxIndex-number);
    }

    /**
     * Push to end.
     *
     * @param cards the cards
     */
    public void pushToEnd(LinkedList<Card> cards) {
        super.cards.addAll(0,cards);
    }
}
