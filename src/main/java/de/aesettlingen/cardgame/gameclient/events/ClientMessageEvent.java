package de.aesettlingen.cardgame.gameclient.events;

import de.aesettlingen.cardgame.commons.event.Event;

import java.util.Objects;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class ClientMessageEvent extends Event {

    private String sender;
    private String message;

    public ClientMessageEvent(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientMessageEvent that = (ClientMessageEvent) o;
        return Objects.equals(sender, that.sender) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, message);
    }
}
