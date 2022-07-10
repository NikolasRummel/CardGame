package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import java.io.Serializable;
import java.util.UUID;

/**
 * The type Abstract packet.
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public abstract class AbstractPacket implements Serializable {

    /**
     * The Sender.
     */
    protected String sender;
    /**
     * The Handshake id.
     */
    protected final UUID handshakeId;

    /**
     * Instantiates a new Abstract packet.
     *
     * @param sender the sender
     */
    public AbstractPacket(String sender) {
        this.sender = sender;
        this.handshakeId = UUID.randomUUID();
    }

    /**
     * Will be called in the PacketListeners and
     * self-triggers its event in the EventBus.
     *
     * @param eventBus the event bus
     */
    public abstract void handle(EventBus eventBus);

    /**
     * Gets sender.
     *
     * @return the sender
     */
    public String getSender() {
        return sender;
    }

    /**
     * Gets handshake id.
     *
     * @return the handshake id
     */
    public UUID getHandshakeId() {
        return handshakeId;
    }
}
