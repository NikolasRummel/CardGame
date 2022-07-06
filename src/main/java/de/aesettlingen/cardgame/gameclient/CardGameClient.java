package de.aesettlingen.cardgame.gameclient;

import de.aesettlingen.cardgame.commons.event.DefaultEventBus;
import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.gameclient.eventlistener.MessageReceiveListener;
import de.aesettlingen.cardgame.gameclient.eventlistener.UserJoinedListener;
import de.aesettlingen.cardgame.gameclient.gui.MainFrame;
import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginMethod;
import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginScreen;

import java.awt.event.WindowEvent;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */

public class CardGameClient {

    private static CardGameClient instance;
    public static CardGameClient getInstance() {
        return instance;
    }

    private String userName;

    private EventBus eventBus;
    private NetworkingClient networkingClient;

    private LoginScreen loginScreen;
    private MainFrame gameGui;

    public CardGameClient() {
        instance = this;

        this.loginScreen = new LoginScreen(new LoginMethod() {
            @Override
            public void login(String loginName) {
                start(loginName);
            }
        });
    }

    public void start(String userName) {
        this.userName = userName;

        this.eventBus = new DefaultEventBus();
        this.networkingClient = new NetworkingClient(
            new NetworkAddress("localhost", 25565),
            userName, eventBus
        );
        this.networkingClient.start();

        this.handleNewScreen();
        this.register();
    }

    public void handleNewScreen() {
        this.loginScreen.dispatchEvent(new WindowEvent(this.loginScreen, WindowEvent.WINDOW_CLOSING));
        this.gameGui = new MainFrame();
    }

    public void sendMessage(String message) {
        this.networkingClient.sendPacket(new MessagePacket(userName, message));
    }

    public void register() {
        this.eventBus.registerListener(new MessageReceiveListener(this));
        this.eventBus.registerListener(new UserJoinedListener(this));
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public NetworkingClient getNetworkingClient() {
        return networkingClient;
    }

    public String getUserName() {
        return userName;
    }

    public MainFrame getGameGui() {
        return gameGui;
    }
}
