package de.aesettlingen.cardgame.gameserver;

import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.commons.networking.packet.UsersPacket;
import de.aesettlingen.cardgame.gameserver.eventlistener.PacketReceiveListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * The type Card game server.
 */
public class CardGameServer {

    private final HashMap<String, PlayerInfo> players;

    private final NetworkingServer networkingServer;

    /**
     * Instantiates a new Card game server.
     */
    public CardGameServer() {
        this.players = new HashMap<>();
        this.networkingServer = new NetworkingServer(25565);

        this.registerListeners();
    }

    /**
     * Add player.
     *
     * @param name the name
     */
    public void addPlayer(String name) {
        if (this.players.containsKey(name)) {
            //this.networkingServer.sendPacket(players.get(name), new ClientMessagePacket("SERVER", "This username is already taken! Please try an other one."));
        } else {
            this.players.put(name, new PlayerInfo()); //TODO: Check if ok?
        }

        System.out.println("Connected Players: ");
        getPlayers().forEach(System.out::println);
    }

    /**
     * Gets players.
     *
     * @return the players
     */
    public Set<String> getPlayers() {
        return this.players.keySet();
    }

    private void registerListeners() {
        this.networkingServer.getEventBus().registerListener(new PacketReceiveListener(this));
    }

    public NetworkingServer getNetworkingServer() {
        return networkingServer;
    }

    /**
     * Start server.
     */
    public void startServer() {
        this.networkingServer.start();
    }
}
