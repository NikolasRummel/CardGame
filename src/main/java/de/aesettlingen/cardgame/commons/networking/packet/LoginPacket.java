package de.aesettlingen.cardgame.commons.networking.packet;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.event.defaultevents.LoginEvent;
import de.aesettlingen.cardgame.commons.networking.listener.ServerPacketListener;

public class LoginPacket extends AbstractPacket {

    public LoginPacket(String sender) {
        super(sender);
    }

    @Override
    public void handle(EventBus eventBus) {
        eventBus.callEvent(new LoginEvent(sender));
    }
}
