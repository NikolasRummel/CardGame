package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public abstract class AbstractPacket implements Serializable {

    protected String sender;
    protected final UUID handshakeId;

    public AbstractPacket(String sender) {
        this.sender = sender;
        this.handshakeId = UUID.randomUUID();
    }

    public abstract void handle(EventBus eventBus);

    public String getSender() {
        return sender;
    }

    public UUID getHandshakeId() {
        return handshakeId;
    }
}
