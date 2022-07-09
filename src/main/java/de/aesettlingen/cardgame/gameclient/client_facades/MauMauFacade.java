package de.aesettlingen.cardgame.gameclient.client_facades;

import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauPlayer;

public abstract class MauMauFacade implements GameFacade {

    public abstract MauMauPlayer getPlayer();

    public abstract Card getTopCard();

    public abstract void sendCardOfMove(Card card);
}