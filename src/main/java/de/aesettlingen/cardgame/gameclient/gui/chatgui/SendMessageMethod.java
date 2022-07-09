package de.aesettlingen.cardgame.gameclient.gui.chatgui;

import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;

@FunctionalInterface
public interface SendMessageMethod {
    void send(String text);
}
