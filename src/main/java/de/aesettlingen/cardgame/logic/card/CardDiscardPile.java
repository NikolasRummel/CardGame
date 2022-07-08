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

    public LinkedList<Card> getShuffeledCards() {
        LinkedList<Card> shuffeled = new LinkedList<>(cards);
        Collections.shuffle(shuffeled);
        return shuffeled;
    }

    public void clear() {
        super.cards.clear();
    }
}