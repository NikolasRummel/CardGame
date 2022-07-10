package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import java.util.UUID;

/**
 * @author Nikolas Rummel
 * @since 10.07.22
 */
public class HandShakePacket extends AbstractPacket {

    private UUID handshakeResponseId;

    public HandShakePacket(String sender, UUID handshakeResponseId) {
        super(sender);
        this.handshakeResponseId = handshakeResponseId;
    }

    public UUID getHandshakeResponseId() {
        return handshakeResponseId;
    }

    @Override
    public void handle(EventBus eventBus) {
    }
}
