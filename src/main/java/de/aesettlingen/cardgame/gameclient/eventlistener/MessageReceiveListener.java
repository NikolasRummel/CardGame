package de.aesettlingen.cardgame.gameclient.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.MessageReceivedEvent;
import de.aesettlingen.cardgame.commons.event.defaultevents.UserJoinedEvent;
import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.gameclient.CardGameClient;

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
    public void onReceiveMessage(MessageReceivedEvent event) {
        //Eigentlich auf Gui anzeigen
        cardGameClient.getGameGui().getChatGui().onReceiveMessage(new MessagePacket(event.getSender(), event.getMessage()));
        System.out.println(event.getSender() + " said: " + event.getMessage());
    }

    @EventHandler
    public void onUserJoined(UserJoinedEvent event) {
        System.out.println("!!!!!!!!!!!!!!!!!UserJoinedListener!!!!!!!!!");
    }
}
