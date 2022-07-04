package de.aesettlingen.cardgame.luncher;

import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.gameserver.CardGameServer;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class BootGameServer {

    public static void main(String[] args) {
        new CardGameServer().startServer();
    }
}
