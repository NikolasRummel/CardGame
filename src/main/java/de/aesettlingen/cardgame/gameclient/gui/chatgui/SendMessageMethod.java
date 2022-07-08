package de.aesettlingen.cardgame.gameclient.gui.chatgui;

import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;

@FunctionalInterface
public interface SendMessageMethod {
    public abstract void send(String text);
}
