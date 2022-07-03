package de.aesettlingen.cardgame.commons.event.defaultevents;

import de.aesettlingen.cardgame.commons.event.Event;
import de.aesettlingen.cardgame.commons.networking.listener.ServerPacketListener;

public class LoginEvent extends Event {

    private String sender;
    private ServerPacketListener serverPacketListener;

    public LoginEvent(String sender) {
        this.sender = sender;
        this.serverPacketListener = serverPacketListener;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public ServerPacketListener getServerPacketListener() {
        return serverPacketListener;
    }

    public void setServerPacketListener(
        ServerPacketListener serverPacketListener) {
        this.serverPacketListener = serverPacketListener;
    }
}
