package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.Game;
import de.aesettlingen.cardgame.logic.GameState;
import de.aesettlingen.cardgame.logic.Move;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardDiscardPile;
import de.aesettlingen.cardgame.logic.card.CardDrawPile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class MauMau extends Game<MauMauPlayer> {

    private final CardDiscardPile cardDiscardPile; // Ablegestapel
    private final CardDrawPile cardDrawPile; // Aufnahmestapel
    private final int startNumberOfCardsPerPlayer = 5;

    private int direction = 1;

    public MauMau() {
        super(new ArrayList<>());

        this.cardDiscardPile = new CardDiscardPile();
        this.cardDrawPile = new CardDrawPile();
    }

    @Override
    public void start() {
        cardDiscardPile.clear();
        cardDrawPile.setCards(distributeCards());
        super.start();
    }

    @Override
    protected void nextPlayer() {
        super.nextPlayer(direction);
    }

    @Override
    public void move(Move move) {
        MauMauMove mauMauMove = (MauMauMove) move;
        if (!isMoveValid(mauMauMove)) return;

        if (mauMauMove.isRaisingACard()){
            letCurrentPlayerRaiseACard();
        } else {
            // TODO: implement that the player has to draw cards before the makes his move
            if (getTopCard().getName().equals("7") && !mauMauMove.getCard().getName().equals("7"))
                letCurrentPlayerRaiseCards(countCardsAtTop("7"));

            // TODO: check if removing an Object still works if the Object was sed through the network
            getCurrentPlayer().getCards().remove(mauMauMove.getCard());
            cardDrawPile.push(mauMauMove.getCard());

            switch (getTopCard().getName()) {
                case "9" -> direction*=-1;
                case "8" -> currentPlayerIndex++;
            }

            nextPlayer();
        }
    }

    private Card getTopCard() {
        return cardDiscardPile.peak();
    }

    private void letCurrentPlayerRaiseACard() {

        if (cardDrawPile.size() == 0) refillCardDrawPile();
        getCurrentPlayer().getCards().add(cardDiscardPile.pull());
    }

    private void letCurrentPlayerRaiseCards(int number) {
        for (int i = 0; i < number; i++)
            letCurrentPlayerRaiseACard();
    }

    private int countCardsAtTop(String nameOfCard) {
        int count = 0;
        for (int i = cardDiscardPile.size()-1; i >= 0; i--)
            if (cardDiscardPile.getCards().get(i).getName().equals(nameOfCard))
                count++;
        return count;
    }

    private void refillCardDrawPile() {
        LinkedList<Card> newCards = (LinkedList<Card>) cardDiscardPile.getCards().subList(0, cardDiscardPile.size() - 2);
        Collections.shuffle(newCards);
        cardDrawPile.pushToEnd(newCards);
        cardDiscardPile.removeAllButLast();
    }


    @Override
    protected boolean isMoveValid(Move move) {
        MauMauMove mauMauMove = (MauMauMove) move;
        return  isTurnOfPlayer(move.getPlayerName()) && getCurrentPlayer().getCards().contains(mauMauMove.getCard()) &&
                (mauMauMove.isRaisingACard() || doesCardFitOnDiscardPile(mauMauMove.getCard()));
    }

    @Override
    public void addPlayer(String name) {
        addPlayer(new MauMauPlayer(name));
    }

    @Override
    public void addPlayer(MauMauPlayer player) {
        players.add(player);
    }

    @Override
    public LinkedList<Card> distributeCards() {
        return super.distributeCards(startNumberOfCardsPerPlayer);
    }

    @Override
    public GameState getStateForPlayer(String playerName) {
        MauMauPlayer player = this.findPlayerByName(playerName);
        if (player == null) return null;

        return new MauMauState(playerName, getNamesOfPlayers(), getNumberOfCardsPerPlayer());
    }

    private MauMauPlayer findPlayerByName(String name) {
        for (MauMauPlayer p : players)
            if (p.getName().equals(name))
                return p;

        return null;
    }

    private ArrayList<String> getNamesOfPlayers() {
        return new ArrayList<>(players.stream().map((MauMauPlayer p) -> p.getName()).toList());
    }

    private ArrayList<Integer> getNumberOfCardsPerPlayer() {
        return new ArrayList<>(players.stream().map((MauMauPlayer p) -> p.getCards().size()).toList());
    }

    private boolean doesCardFitOnDiscardPile(Card card) {
        return cardDiscardPile.size() == 0 || doCardsFotTogether(card, cardDiscardPile.peak());
    }

    private boolean doCardsFotTogether(Card cardA, Card cardB) {
        return cardA.getColor().equals(cardB.getColor()) || cardA.getName().equals(cardB.getName());
    }
}