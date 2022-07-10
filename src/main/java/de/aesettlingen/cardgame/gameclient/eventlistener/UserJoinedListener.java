package de.aesettlingen.cardgame.gameclient.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.UserJoinedEvent;
import de.aesettlingen.cardgame.gameclient.CardGameClient;

public class UserJoinedListener implements EventListener {

    private final CardGameClient cardGameClient;

    public UserJoinedListener(CardGameClient cardGameClient) {
        this.cardGameClient = cardGameClient;
    }

    @EventHandler
    public void onUserJoined(UserJoinedEvent event) {
        cardGameClient.getGameGui().getWaitingScreenPanel().setPlayers(event.getUsers());
    }
}
