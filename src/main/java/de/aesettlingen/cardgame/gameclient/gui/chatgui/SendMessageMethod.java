package de.aesettlingen.cardgame.gameclient.gui.chatgui;

@FunctionalInterface
public interface SendMessageMethod {
    void send(String text);
}
