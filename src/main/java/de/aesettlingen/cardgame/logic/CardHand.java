package de.aesettlingen.cardgame.logic;


import java.util.LinkedList;
import java.util.Random;

public class CardHand extends CardCollection {

    private Random random = new Random();

    public Card pullRandom() {
        return cards.remove(random.nextInt(cards.size()));
    }
    public LinkedList<Card> pullRandom(int number) {
        LinkedList<Card> randomCards = new LinkedList<>();
        for (int i = 0; i < number; i++)
            randomCards.add(pullRandom());

        return randomCards;
    }

    public void add(Card card) {
        super.cards.add(card);
    }
+ add(card: LinkedList<Card>)
}
