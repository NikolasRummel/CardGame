package de.aesettlingen.cardgame.logic.card;

import java.util.ArrayList;
import java.util.LinkedList;


public class CardStack extends CardCollection{

    // functions

    public void push(Card card) {
        super.cards.add(card);
    }

    public Card pull() {
       return super.cards.removeLast();
    }

    public LinkedList<Card> pull(int number) {
        int maxIndex = super.cards.size()-1;
        return (LinkedList<Card>) super.cards.subList(maxIndex-number, maxIndex);
    }

    public void push(LinkedList<Card> cards) {
        super.cards.addAll(cards);
    }

    public void pushToEnd(Card card) {
        super.cards.addFirst(card);
    }

    public Card pullToEnd() {
        return super.cards.removeFirst();
    }

    public LinkedList<Card> pullToEnd(int number) {
        int maxIndex = super.cards.size()-1;
        return (LinkedList<Card>) super.cards.subList(0, maxIndex-number);
    }

    public void pushToEnd(LinkedList<Card> cards) {
        super.cards.addAll(0,cards);
    }
}
