package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;

abstract public class CardCollection {
    protected LinkedList<Card> cards;

    public CardCollection() {
        this.cards = new LinkedList<>();
    }

    public CardCollection(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public CardCollection(Card ... cards) {
        this.cards = new LinkedList<>();
        for (Card card : cards)
            this.cards.addLast(card);
    }

    public LinkedList<Card> getCards() {
        return new LinkedList<>(cards);
    }

    public void setCards(LinkedList<Card> cards) {
        this.cards = cards;
    }

    public int size() {
        return cards.size();
    }

    public boolean isEmpty() {
        return cards.size()  == 0;
    }
    public boolean isNotEmpty(){
        return cards.size()  != 0;
    }
}