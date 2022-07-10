package de.aesettlingen.cardgame.logic;

import de.aesettlingen.cardgame.logic.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * The type Game.
 * For multiple type of games, for instance MauMau
 *
 * @param <T> the type parameter
 */
abstract public class Game<T extends Player> {

    /**
     * The Max number of players.
     */
    protected int maxNumberOfPlayers;
    /**
     * The Players.
     */
    protected ArrayList<T> players;

    /**
     * The Current player index.
     */
    protected int currentPlayerIndex;
    /**
     * The Is started.
     */
    protected boolean isStarted = false;

    /**
     * The Start mode.
     */
    protected StartMode startMode;

    /**
     * The Card deck.
     */
    protected final ArrayList<Card> cardDeck;

    /**
     * Instantiates a new Game.
     *
     * @param players            the players
     * @param maxNumberOfPlayers the max number of players
     * @param isStarted          the is started
     * @param startMode          the start mode
     * @param cardDeck           the card deck
     */
    public Game(ArrayList<T> players, int maxNumberOfPlayers, boolean isStarted,
                StartMode startMode, ArrayList<Card> cardDeck) {
        this.players = players;
        if (isStarted) start();
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.startMode = startMode;
        this.cardDeck = cardDeck;
    }

    /**
     * Instantiates a new Game.
     *
     * @param players            the players
     * @param maxNumberOfPlayers the max number of players
     * @param cardDeck           the card deck
     */
    public Game(ArrayList<T> players, int maxNumberOfPlayers, ArrayList<Card> cardDeck) {
        this.players = players;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.cardDeck = cardDeck;
        this.startMode = StartMode.RANDOM;
    }

    /**
     * Instantiates a new Game.
     *
     * @param cardDeck the card deck
     */
    public Game(ArrayList<Card> cardDeck) {
        this.cardDeck = cardDeck;
        this.players = new ArrayList<>();
        this.startMode = StartMode.RANDOM;
    }

    /**
     * Gets current player.
     *
     * @return the current player
     */
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    /**
     * Next player.
     */
    protected void nextPlayer() {
        nextPlayer(1);
    }

    /**
     * Next player.
     *
     * @param step the step
     */
    protected void nextPlayer(int step) {
        currentPlayerIndex = currentPlayerIndex+step;
        if (currentPlayerIndex >= 0)
            currentPlayerIndex%=players.size();
        else
            while (currentPlayerIndex < 0)
                currentPlayerIndex=players.size()+currentPlayerIndex;
    }

    /**
     * Move.
     *
     * @param move the move
     */
    public abstract void move(Move move);

    /**
     * Is move valid boolean.
     *
     * @param move the move
     * @return the boolean
     */
    protected abstract boolean isMoveValid(Move move);

    /**
     * Is turn of player boolean.
     *
     * @param name the name
     * @return the boolean
     */
    public boolean isTurnOfPlayer(String name) {
        return getCurrentPlayer().getName().equals(name);
    }

    /**
     * Is turn of player boolean.
     *
     * @param player the player
     * @return the boolean
     */
    public boolean isTurnOfPlayer(T player) {
        return getCurrentPlayer().equals(player);
    }

    /**
     * Is started boolean.
     *
     * @return the boolean
     */
    public boolean isStarted() {
        return this.isStarted;
    }

    /**
     * Start.
     */
    public void start() {

        switch (startMode) {
            case FIRST -> currentPlayerIndex = 0;
            case RANDOM -> selectRandomPlayer();
        }

        this.isStarted = true;
    }

    /**
     * Select random player.
     */
    public void selectRandomPlayer() {
        if (players.size() == 0) return;
        currentPlayerIndex = (int) (Math.random() * players.size());
    }

    /**
     * Add player.
     *
     * @param name the name
     */
    public abstract void addPlayer(String name);

    /**
     * Add player.
     *
     * @param player the player
     */
    public abstract void addPlayer(T player);

    /**
     * Gets last added player.
     *
     * @return the last added player
     */
    public T getLastAddedPlayer() {
        return players.get(players.size() - 1);
    }

    /**
     * Distribute cards linked list.
     *
     * @return the linked list
     */
    protected abstract LinkedList<Card> distributeCards();

    /**
     * Distribute cards linked list.
     *
     * @param cardsPerPlayer the cards per player
     * @return leftover cards
     */
    protected LinkedList<Card> distributeCards(int cardsPerPlayer) {
        LinkedList<Card> shuffledDeck = getShuffledDeck();
        clearCardsOfPlayers();
        for (int i = 0; i < shuffledDeck.size(); i++) {
            if (i >= cardsPerPlayer*players.size())
                return (LinkedList<Card>) shuffledDeck.subList(0, i-1);
            players.get(i%players.size()).getCards().add(shuffledDeck.get(i));
        }

        return new LinkedList<>();
    }

    /**
     * Gets shuffled deck.
     *
     * @return the shuffled deck
     */
    protected LinkedList<Card> getShuffledDeck() {
        LinkedList<Card> shuffledDeck = new LinkedList<>(cardDeck);
        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }

    /**
     * Clear cards of players.
     */
    protected void clearCardsOfPlayers() {
        players.forEach(Player::clearHand);
    }

    /**
     * Gets state for player.
     *
     * @param playerName the player name
     * @return the state for player
     */
    public abstract GameState getStateForPlayer(String playerName);
}