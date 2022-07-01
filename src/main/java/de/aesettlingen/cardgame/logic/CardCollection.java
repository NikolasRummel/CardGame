package de.aesettlingen.cardgame.logic;

import java.util.LinkedList;

abstract public class CardCollection {
    protected LinkedList<Card> cards;

    CardCollection() {
        this.cards = new LinkedList<Card>();
    }

    CardCollection(LinkedList<Card> cards) {
        this.cards = cards;
    }

    CardCollection(Card ... cards) {
        this.cards = new LinkedList<>();
        for (Card card : cards)
            this.cards.addLast(card);
    }

    public LinkedList<Card> getCards() {
        return cards;
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