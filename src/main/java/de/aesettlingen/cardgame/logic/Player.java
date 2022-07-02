package de.aesettlingen.cardgame.logic;

import java.util.LinkedList;

abstract class Player {
    protected final CardHand hand;
    protected final String name;
    protected boolean hasePermissionToMove = false;

    Player(String name) {
        this.name = name;
        this.hand = new CardHand();
    }

    Player(String name, CardHand hand) {
        this.name = name;
        this.hand = hand;
    }

    Player(String name, LinkedList<Card> cards) {
        this.name = name;
        this.hand = new CardHand(cards);
    }

    public int getPoints() {
        return hand.getCombinedValue();
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
}