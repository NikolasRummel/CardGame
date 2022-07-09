package de.aesettlingen.cardgame.gameclient.client_facades;

import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.SelectCardMethod;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauMove;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauPlayer;

public abstract class GameFacade {

    public abstract MauMauPlayer getPlayer();

    public abstract Card getTopCard();

    public abstract void sendCardOfMove(Card Move);
}