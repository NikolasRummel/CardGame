package de.aesettlingen.cardgame.logic.card;

import java.util.ArrayList;
import java.util.LinkedList;

// TODO: implement and change according to the class diagram
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

    }

    public Card pullToEnd() {
        return null;
    }

    public LinkedList<Card> pullToEnd(int number) {
        return null;
    }

    public void pushToEnd(LinkedList<Card> cards) {

    }
}
