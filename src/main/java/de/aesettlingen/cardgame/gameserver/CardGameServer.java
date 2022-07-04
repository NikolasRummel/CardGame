package de.aesettlingen.cardgame.gameserver;

import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.commons.networking.packet.UsersPacket;
import de.aesettlingen.cardgame.gameclient.CardGameClient;
import de.aesettlingen.cardgame.gameserver.eventlistener.LoginReceiveListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class CardGameServer {

    private HashMap<String, PlayerInfo> players;

    private NetworkingServer networkingServer;

    public CardGameServer() {
        this.players = new HashMap<>();
        this.networkingServer = new NetworkingServer(25565);

        this.registerListeners();
    }

    public void addPlayer(String name) {
        if(this.players.keySet().contains(name)) {
            //this.networkingServer.sendPacket(players.get(name), new ClientMessagePacket("SERVER", "This username is already taken! Please try an other one."));
        } else {
            this.players.put(name, new PlayerInfo()); //TODO: Check if ok?

            ArrayList<String> list = new ArrayList<>(getPlayers());
            this.networkingServer.broadcastPacket(new UsersPacket("SERVER", list));
        }

        System.out.println("Connected Players: ");
        getPlayers().forEach(System.out::println);
    }

    public Set<String> getPlayers() {
        return this.players.keySet();
    }

    private void registerListeners() {
        this.networkingServer.getEventBus().registerListener(new LoginReceiveListener(this));
    }

    public void startServer() {
        this.networkingServer.start();
    }
}
