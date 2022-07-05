package de.aesettlingen.cardgame.commons.event.defaultevents;

import de.aesettlingen.cardgame.commons.event.Event;

public class RequestUserEvent extends Event {

    private String sender;

    public RequestUserEvent(String sender) {
        this.sender = sender;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
