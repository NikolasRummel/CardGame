package de.aesettlingen.cardgame.gameserver;

import de.aesettlingen.cardgame.commons.networking.NetworkAddress;
import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.commons.networking.listener.ServerPacketListener;
import de.aesettlingen.cardgame.commons.networking.packet.ClientMessagePacket;
import de.aesettlingen.cardgame.gameserver.eventlistener.LoginReceiveListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CardGameServer {

    private HashMap<String, ServerPacketListener> players;

    private NetworkingServer networkingServer;

    public CardGameServer() {
        this.players = new HashMap<>();
        this.networkingServer = new NetworkingServer(25565);

        this.registerListeners();
    }

    public void addPlayer(String name, ServerPacketListener handler) {
        if(this.players.keySet().contains(name)) {
            this.networkingServer.sendPacket(players.get(name), new ClientMessagePacket("SERVER", "This username is already taken! Please try an other one."));
        }else {
            this.players.put(name, handler);
        }
    }

    private void registerListeners() {
        this.networkingServer.getEventBus().registerListener(new LoginReceiveListener(this));
    }

    public void startServer() {
        this.networkingServer.start();
    }
}
