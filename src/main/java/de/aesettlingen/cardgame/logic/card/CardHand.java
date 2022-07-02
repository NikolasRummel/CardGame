package de.aesettlingen.cardgame.logic.card;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class CardHand extends CardCollection {

    public CardHand() {
        super();
    }

    CardHand(LinkedList<Card> cards) {
        super(cards);
    }

    CardHand(Card ... cards) {
        super(cards);
    }

    public int getCombinedValue() {
        AtomicInteger out = new AtomicInteger();
        cards.forEach((Card it) -> out.addAndGet(it.getValue()));
        return out.get();
    }

    public Card pullRandom() {
        return cards.remove((int) (Math.random()*cards.size()));
    }

    public LinkedList<Card> pullRandom(int number) {
        LinkedList<Card> out = new LinkedList<>();
        for (int i = 0; i < number; i++)
            out.add(pullRandom());
        return out;
    }

    public void add(Card card) {
        super.cards.add(card);
    }

    public void add(LinkedList<Card> card) {
        super.cards.addAll(card);
    }
}