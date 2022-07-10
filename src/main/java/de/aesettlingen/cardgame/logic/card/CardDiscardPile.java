package de.aesettlingen.cardgame.logic.card;

import java.util.Collections;
import java.util.LinkedList;

public class CardDiscardPile extends CardStack{

    public CardDiscardPile() {
        super();
    }

    public CardDiscardPile(LinkedList<Card> cards) {
        super(cards);
    }

    public CardDiscardPile(Card... cards) {
        super(cards);
    }

    public LinkedList<Card> getShuffledCards() {
        LinkedList<Card> shuffled = new LinkedList<>(cards);
        Collections.shuffle(shuffled);
        return shuffled;
    }

    public Card peak() {
        return cards.getLast();
    }

    public void removeAllButLast() {
        this.setCards(this.peak());
    }
}