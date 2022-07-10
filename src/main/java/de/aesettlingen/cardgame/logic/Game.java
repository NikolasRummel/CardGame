package de.aesettlingen.cardgame.logic;

import de.aesettlingen.cardgame.logic.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

abstract public class Game<T extends Player> {

    protected int maxNumberOfPlayers;
    protected ArrayList<T> players;

    protected int currentPlayerIndex;
    protected boolean isStarted = false;

    protected StartMode startMode;

    protected final ArrayList<Card> cardDeck;

    public Game(ArrayList<T> players, int maxNumberOfPlayers, boolean isStarted,
                StartMode startMode, ArrayList<Card> cardDeck) {
        this.players = players;
        this.isStarted = isStarted;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.startMode = startMode;
        this.cardDeck = cardDeck;
    }

    public Game(ArrayList<T> players, int maxNumberOfPlayers, ArrayList<Card> cardDeck) {
        this.players = players;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.cardDeck = cardDeck;
        this.startMode = StartMode.RANDOM;
    }

    public Game(ArrayList<Card> cardDeck) {
        this.cardDeck = cardDeck;
        this.players = new ArrayList<>();
        this.startMode = StartMode.RANDOM;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    protected void nextPlayer() {
        nextPlayer(1);
    }

    protected void nextPlayer(int step) {
        currentPlayerIndex = currentPlayerIndex+step;
        if (currentPlayerIndex >= 0)
            currentPlayerIndex%=players.size();
        else
            while (currentPlayerIndex < 0)
                currentPlayerIndex=players.size()+currentPlayerIndex;
    }

    abstract public void move(Move move);

    protected abstract boolean isMoveValid(Move move);

    public boolean isTurnOfPlayer(String name) {
        return getCurrentPlayer().getName().equals(name);
    }

    public boolean isTurnOfPlayer(T player) {
        return getCurrentPlayer().equals(player);
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public void start() {

        switch (startMode) {
            case FIRST -> currentPlayerIndex = 0;
            case RANDOM -> selectRandomPlayer();
        }

        this.isStarted = true;
    }

    protected void selectRandomPlayer() {
        if (players.size() == 0) return;
        currentPlayerIndex = (int) (Math.random() * players.size());
    }

    protected abstract void addPlayer(String name);

    protected abstract void addPlayer(T player);

    public T getLastAddedPlayer() {
        return players.get(players.size() - 1);
    }

    abstract public LinkedList<Card> distributeCards();


    /**
     * @param cardsPerPlayer
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

    protected LinkedList<Card> getShuffledDeck() {
        LinkedList<Card> shuffledDeck = new LinkedList<>(cardDeck);
        Collections.shuffle(shuffledDeck);
        return shuffledDeck;
    }

    protected void clearCardsOfPlayers() {
        players.forEach(Player::clearHand);
    }

    public abstract GameState getStateForPlayer(String playerName);
}