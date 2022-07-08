package de.aesettlingen.cardgame.logic;

import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;
import java.util.LinkedList;

public abstract class Player {

    protected final CardHand hand;
    protected final String name;
    protected boolean hasePermissionToMove = false;

    public Player(String name) {
        this.name = name;
        this.hand = new CardHand();
    }

    public Player(String name, CardHand hand) {
        this.name = name;
        this.hand = hand;
    }

    public Player(String name, LinkedList<Card> cards) {
        this.name = name;
        this.hand = new CardHand(cards);
    }

    public String getName() {
        return this.name;
    }

    public void setCards(LinkedList<Card> cards) {
        this.hand.setCards(cards);
    }

    public LinkedList<Card> getCards() {
        return this.hand.getCards();
    }

    public void grantPermissionToMove() {
        hasePermissionToMove = true;
    }

    public void depriveToMove() {
        hasePermissionToMove = false;
    }

    public void clearHand() {
        this.hand.clear();
    }
}