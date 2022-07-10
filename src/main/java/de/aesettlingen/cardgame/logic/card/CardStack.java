package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;


public class CardStack extends CardCollection {


    public CardStack() {
        super();
    }

    public CardStack(LinkedList<Card> cards) {
        super(cards);
    }

    public CardStack(Card... cards) {
        super(cards);
    }

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

    public void pushFromEnd(Card card) {
        super.cards.addFirst(card);
    }

    public Card pullFromEnd() {
        return super.cards.removeFirst();
    }

    public LinkedList<Card> pullFromEnd(int number) {
        int maxIndex = super.cards.size()-1;
        return (LinkedList<Card>) super.cards.subList(0, maxIndex-number);
    }

    public void pushFromEnd(LinkedList<Card> cards) {
        super.cards.addAll(0,cards);
    }
}
