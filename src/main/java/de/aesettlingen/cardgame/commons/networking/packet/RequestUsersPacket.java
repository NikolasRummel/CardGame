package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.event.defaultevents.RequestUserEvent;

public class RequestUsersPacket extends AbstractPacket {

    public RequestUsersPacket(String sender) {
        super(sender);
    }

    @Override
    public void handle(EventBus eventBus) {
        eventBus.callEvent(new RequestUserEvent(sender));
    }
}
