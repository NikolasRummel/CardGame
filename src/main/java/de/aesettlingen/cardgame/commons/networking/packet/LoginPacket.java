package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;

public class LoginPacket extends AbstractPacket {

    public LoginPacket(String sender) {
        super(sender);
    }

    @Override
    public void handle(EventBus eventBus) {

    }
}
