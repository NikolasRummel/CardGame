package de.aesettlingen.cardgame.luncher;

import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.gameclient.CardGameClient;
import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginMethod;
import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginScreen;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class BootGameClient {

    public static void main(String[] args) {
        new CardGameClient();
    }
}
