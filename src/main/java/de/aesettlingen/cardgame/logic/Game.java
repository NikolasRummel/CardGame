package de.aesettlingen.cardgame.logic;

import java.util.ArrayList;

abstract public class Game<T extends Player> {

    protected int maxNumberOfPlayers;
    protected ArrayList<T> players;

    protected int currentPlayerIndex;
    protected boolean isStarted = false;

    protected StartMode startMode;

    public Game(ArrayList<T> players, int maxNumberOfPlayers, boolean isStarted,
        StartMode startMode) {
        this.players = players;
        this.isStarted = isStarted;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.startMode = startMode;
    }

    public Game(ArrayList<T> players, int maxNumberOfPlayers) {
        this.players = players;
        this.maxNumberOfPlayers = maxNumberOfPlayers;
        this.startMode = StartMode.RANDOM;
    }

    public Game() {
        this.players = new ArrayList<>();
        this.startMode = StartMode.RANDOM;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }

    public void nextPlayer() {
        currentPlayerIndex++;
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
        this.isStarted = true;
    }

    protected abstract void addPlayer(String name);

    abstract void addPlayer(T player);

    public T getLastAddedPlayer() {
        return players.get(players.size() - 1);
    }
}