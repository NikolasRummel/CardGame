package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.gameclient.events.ClientMessageEvent;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class ClientMessagePacket extends AbstractPacket {

    private final String message;
    private final long timestamp;

    public ClientMessagePacket(String sender, String message) {
        super(sender);
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public void handle(EventBus eventBus) {
        eventBus.callEvent(new ClientMessageEvent(sender, message));
    }

    @Override
    public String getSender() {
        return super.getSender();
    }

    public String getMessage() {
        return message;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
