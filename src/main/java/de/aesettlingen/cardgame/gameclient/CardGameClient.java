package de.aesettlingen.cardgame.gameclient;

import de.aesettlingen.cardgame.commons.event.DefaultEventBus;
import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.commons.networking.packet.ClientMessagePacket;
import de.aesettlingen.cardgame.gameclient.eventlistener.MessageReceiveListener;
import lombok.Getter;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
@Getter
public class CardGameClient {

    private String userName;

    private final EventBus eventBus;
    private final NetworkingClient networkingClient;

    public CardGameClient(String userName) {
        this.userName = userName;

        this.eventBus = new DefaultEventBus();
        this.networkingClient = new NetworkingClient(
            new NetworkAddress("localhost", 123),
            userName,
            eventBus
        );

        this.register();
    }

    public void sendMessage(String message) {
        this.networkingClient.sendPacket(new ClientMessagePacket(eventBus, userName, message));
    }

    public void register() {
        this.eventBus.registerListener(new MessageReceiveListener(this));
    }

}
