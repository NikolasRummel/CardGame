package de.aesettlingen.cardgame.gameclient.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.MessageReceivedEvent;
import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.gameclient.CardGameClient;

/**
 * The type Message receive listener.
 *
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MessageReceiveListener implements EventListener {

    private final CardGameClient cardGameClient;

    /**
     * Instantiates a new Message receive listener.
     *
     * @param cardGameClient the card game client
     */
    public MessageReceiveListener(CardGameClient cardGameClient) {
        this.cardGameClient = cardGameClient;
    }

    /**
     * The event when a Messages is received.
     *
     * @param event the event
     */
    @EventHandler
    public void onReceiveMessage(MessageReceivedEvent event) {
        cardGameClient.getGameGui().getChatPanel()
            .onReceiveMessage(new MessagePacket(event.getSender(), event.getMessage()));
        System.out.println(event.getSender() + " said: " + event.getMessage());
    }

}
