package de.aesettlingen.cardgame.gameclient;

import de.aesettlingen.cardgame.commons.event.DefaultEventBus;
import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
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

    public CardGameClient() {
        this.game = new MauMau();

       this.gameFacade = new MauMauFacade() {
           @Override
           public MauMauPlayer getPlayer() {
               return null;
           }

           @Override
           public Card getTopCard() {
               return new Card("jack", "spades");
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
               sendMessage(text);
           }
       };

        this.loginScreen = new LoginScreenPanel(this::start);
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
        this.loginScreen.dispatchEvent(
            new WindowEvent(this.loginScreen, WindowEvent.WINDOW_CLOSING));
        this.gameGui = new MainFrame(gameFacade, chatFacade);
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