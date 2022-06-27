package de.aesettlingen.cardgame.gameserver;

import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.gameserver.eventlistener.LoginReceiveListener;

import java.util.ArrayList;
import java.util.List;

public class CardGameServer {

    private List<String> players;

    private NetworkingServer networkingServer;

    public CardGameServer() {
        this.players = new ArrayList<>();
        this.networkingServer = new NetworkingServer(25565);

        this.registerListeners();
    }

    public void addPlayer(String name) {
        this.players.add(name);
    }

    private void registerListeners() {
        this.networkingServer.getEventBus().registerListener(new LoginReceiveListener());
    }

    public void startServer() {
        this.networkingServer.start();
    }
}
