package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The type Card hand.
 * Equals the cards each player has
 */
public class CardHand extends CardCollection {

    /**
     * Instantiates a new Card hand.
     */
    public CardHand() {
        super();
    }

    /**
     * Instantiates a new Card hand.
     *
     * @param cards the cards
     */
    public CardHand(LinkedList<Card> cards) {
        super(cards);
    }

    /**
     * Instantiates a new Card hand.
     *
     * @param cards the cards
     */
    public CardHand(Card... cards) {
        super(cards);
    }

    /**
     * Gets combined value.
     *
     * @return the combined value
     */
    public int getCombinedValue() {
        AtomicInteger out = new AtomicInteger();
        cards.forEach((Card it) -> out.addAndGet(it.getValue()));
        return out.get();
    }

    /**
     * Pull random card.
     *
     * @return the card
     */
    public Card pullRandom() {
        return cards.remove((int) (Math.random() * cards.size()));
    }

    /**
     * Pull random linked list.
     *
     * @param number the number
     * @return the linked list
     */
    public LinkedList<Card> pullRandom(int number) {
        LinkedList<Card> out = new LinkedList<>();
        for (int i = 0; i < number; i++) {
            out.add(pullRandom());
        }
        return out;
    }

    /**
     * Remove card card.
     *
     * @param index the index
     * @return the card
     */
    public Card removeCard(int index) {
        return cards.remove(index);
    }

    /**
     * Remove card boolean.
     *
     * @param card the card
     * @return the boolean
     */
    public boolean removeCard(Card card) {
        return cards.remove(card);
    }

    /**
     * Add.
     *
     * @param card the card
     */
    public void add(Card card) {
        super.cards.add(card);
    }

    /**
     * Add.
     *
     * @param card the card
     */
    public void add(LinkedList<Card> card) {
        super.cards.addAll(card);
    }
}