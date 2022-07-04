package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.event.defaultevents.MessageReceivedEvent;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class MessagePacket extends AbstractPacket {

    private final String message;
    private final long timestamp;

    public MessagePacket(String sender, String message) {
        super(sender);
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    @Override
    public void handle(EventBus eventBus) {
        eventBus.callEvent(new MessageReceivedEvent(sender, message));
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
