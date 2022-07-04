package de.aesettlingen.cardgame.gameclient.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.UserJoinedEvent;
import de.aesettlingen.cardgame.gameclient.CardGameClient;
import de.aesettlingen.cardgame.gameclient.events.ClientMessageEvent;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MessageReceiveListener implements EventListener {

    private final CardGameClient cardGameClient;

    public MessageReceiveListener(CardGameClient cardGameClient) {
        this.cardGameClient = cardGameClient;

    }

    @EventHandler
    public void onReceiveMessage(ClientMessageEvent event) {
        //Eigentlich auf Gui anzeigen
        System.out.println(event.getSender() + " said: " + event.getMessage());
    }

    @EventHandler
    public void onUserJoined(UserJoinedEvent event) {
        System.out.println("hihihihihihihiii");
        cardGameClient.getGameGui().getWaitingScreenPanel().setPlayers(event.getUsers());

    }
}
