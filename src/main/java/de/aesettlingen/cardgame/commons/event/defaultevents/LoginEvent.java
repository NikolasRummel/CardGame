package de.aesettlingen.cardgame.commons.event.defaultevents;

import de.aesettlingen.cardgame.commons.event.Event;
import de.aesettlingen.cardgame.commons.networking.listener.ServerPacketListener;

public class LoginEvent extends Event {

    private String sender;
    public LoginEvent(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
