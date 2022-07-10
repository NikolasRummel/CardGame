package de.aesettlingen.cardgame.gameclient.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.UserJoinedEvent;
import de.aesettlingen.cardgame.gameclient.CardGameClient;

/**
 * The type User joined listener.
 */
public class UserJoinedListener implements EventListener {

    private final CardGameClient cardGameClient;

    /**
     * Instantiates a new User joined listener.
     *
     * @param cardGameClient the card game client
     */
    public UserJoinedListener(CardGameClient cardGameClient) {
        this.cardGameClient = cardGameClient;
    }


    /**
     * The event when a new User joined the Game
     *
     * @param event the event
     */
    @EventHandler
    public void onUserJoined(UserJoinedEvent event) {
        cardGameClient.getGameGui().getWaitingScreenPanel().setPlayers(event.getUsers());
    }
}
