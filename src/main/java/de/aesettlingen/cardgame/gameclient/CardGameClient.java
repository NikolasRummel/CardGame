package de.aesettlingen.cardgame.gameclient;

import de.aesettlingen.cardgame.commons.event.DefaultEventBus;
import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.commons.networking.packet.RequestUsersPacket;
import de.aesettlingen.cardgame.gameclient.client_facades.ChatFacade;
import de.aesettlingen.cardgame.gameclient.client_facades.MauMauFacade;
import de.aesettlingen.cardgame.gameclient.eventlistener.MessageReceiveListener;
import de.aesettlingen.cardgame.gameclient.eventlistener.UserJoinedListener;
import de.aesettlingen.cardgame.gameclient.gui.MainFrame;
import de.aesettlingen.cardgame.gameclient.gui.login_screen.LoginScreenPanel;
import de.aesettlingen.cardgame.logic.Game;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.mau_mau.MauMau;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauMove;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauPlayer;

import java.awt.event.WindowEvent;

/**
 * The type Card game client.
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class CardGameClient {

    private String userName;

    private EventBus eventBus;
    private NetworkingClient networkingClient;

    private final LoginScreenPanel loginScreen;
    private MainFrame gameGui;

    private final MauMauFacade gameFacade;
    private final ChatFacade chatFacade;
    private final Game<MauMauPlayer> game;

    /**
     * Instantiates a new Card game client.
     */
    public CardGameClient() {
        this.game = new MauMau();

       this.gameFacade = new MauMauFacade() {
           @Override
           public String getNameOfPlayer() {
               return userName;
           }
           @Override
           public void sendCardOfMove(Card card) {
               MauMauMove move = new MauMauMove(userName, card);
               // TODO: implement
           }
       };
       this.chatFacade = new ChatFacade() {
           @Override
           public void sendMessage(String text) {
               CardGameClient.this.sendMessage(text);
           }
       };

        this.loginScreen = new LoginScreenPanel(this::start);
    }

    /**
     * Starts the client and opens the connection to the server
     *
     * @param userName the user name
     */
    public void start(String userName) {
        this.userName = userName;
        this.game.addPlayer(userName);

        this.eventBus = new DefaultEventBus();
        this.networkingClient = new NetworkingClient(
            this,
            new NetworkAddress("localhost", 25565),
            userName, eventBus
        );
        this.networkingClient.start();

        this.handleNewScreen();
        this.register();
    }

    /**
     * Handle new screen. (from login to waiting gui)
     */
    public void handleNewScreen() {
        this.loginScreen.dispatchEvent(
            new WindowEvent(this.loginScreen, WindowEvent.WINDOW_CLOSING));
        this.gameGui = new MainFrame(gameFacade, chatFacade); //Wating screen
        this.networkingClient.sendPacket(new RequestUsersPacket(this.userName));
    }

    /**
     * Send a message to the server
     *
     * @param message the message
     */
    public void sendMessage(String message) {
        this.networkingClient.sendPacket(new MessagePacket(userName, message));
    }

    /**
     * Register.
     */
    public void register() {
        this.eventBus.registerListener(new MessageReceiveListener(this));
        this.eventBus.registerListener(new UserJoinedListener(this));
    }


    /**
     * Gets event bus.
     *
     * @return the event bus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Gets networking client.
     *
     * @return the networking client
     */
    public NetworkingClient getNetworkingClient() {
        return networkingClient;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets game gui.
     *
     * @return the game gui
     */
    public MainFrame getGameGui() {
        return gameGui;
    }
}