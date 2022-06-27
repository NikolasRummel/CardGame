package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.Event;
import de.aesettlingen.cardgame.commons.event.EventBus;
import java.io.Serializable;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public abstract class AbstractPacket implements Serializable {

    protected EventBus eventBus;
    protected String sender;

    public AbstractPacket(String sender) {
        this.eventBus = eventBus;
        this.sender = sender;
    }

    public abstract void handle(EventBus eventBus);

}
