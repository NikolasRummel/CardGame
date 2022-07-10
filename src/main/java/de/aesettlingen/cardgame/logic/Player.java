package de.aesettlingen.cardgame.logic;

import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;
import java.util.LinkedList;

/**
 * The type Player.
 */
public abstract class Player {

    /**
     * The Hand.
     */
    protected final CardHand hand;
    /**
     * The Name.
     */
    protected final String name;
    /**
     * The Hase permission to move.
     */
    protected boolean hasePermissionToMove = false;

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     */
    public Player(String name) {
        this.name = name;
        this.hand = new CardHand();
    }

    /**
     * Instantiates a new Player.
     *
     * @param name the name
     * @param hand the hand
     */
    public Player(String name, CardHand hand) {
        this.name = name;
        this.hand = hand;
    }

    /**
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param cards the cards
     */
    public Player(String name, LinkedList<Card> cards) {
        this.name = name;
        this.hand = new CardHand(cards);
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets cards.
     *
     * @param cards the cards
     */
    public void setCards(LinkedList<Card> cards) {
        this.hand.setCards(cards);
    }

    /**
     * Gets cards.
     *
     * @return the cards
     */
    public LinkedList<Card> getCards() {
        return this.hand.getCards();
    }

    /**
     * Grant permission to move.
     */
    public void grantPermissionToMove() {
        hasePermissionToMove = true;
    }

    /**
     * Deprive permission to move.
     */
    public void deprivePermissionToMove() {
        hasePermissionToMove = false;
    }

    /**
     * Clear hand.
     */
    public void clearHand() {
        this.hand.clear();
    }

    /**
     * Gets hand.
     *
     * @return the hand
     */
    public CardHand getHand() {
        return hand;
    }
}