package de.aesettlingen.cardgame.gameclient.client_facades;

import de.aesettlingen.cardgame.logic.card.Card;

public abstract class MauMauFacade implements GameFacade {

    public abstract void sendCardOfMove(Card card);
}