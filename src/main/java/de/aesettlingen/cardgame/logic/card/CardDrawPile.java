package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;

public class CardDrawPile extends CardStack {

    public CardDrawPile() {
        super();
    }

    public CardDrawPile(LinkedList<Card> cards) {
        super(cards);
    }

    public CardDrawPile(Card... cards) {
        super(cards);
    }
}
