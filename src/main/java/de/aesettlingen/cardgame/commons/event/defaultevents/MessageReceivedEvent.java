package de.aesettlingen.cardgame.commons.event.defaultevents;

import de.aesettlingen.cardgame.commons.event.Event;

/**
 * @author Nikolas Rummel
 * @since 04.07.22
 */
public class MessageReceivedEvent extends Event {

    private String sender;
    private String message;

    public MessageReceivedEvent(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

}
