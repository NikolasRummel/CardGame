package de.aesettlingen.cardgame.gameserver.eventlistener;

import de.aesettlingen.cardgame.commons.event.EventHandler;
import de.aesettlingen.cardgame.commons.event.EventListener;
import de.aesettlingen.cardgame.commons.event.defaultevents.LoginEvent;
import de.aesettlingen.cardgame.commons.event.defaultevents.MessageReceivedEvent;
import de.aesettlingen.cardgame.commons.event.defaultevents.RequestUserEvent;
import de.aesettlingen.cardgame.commons.networking.packet.MessagePacket;
import de.aesettlingen.cardgame.commons.networking.packet.UsersPacket;
import de.aesettlingen.cardgame.gameserver.CardGameServer;
import java.util.ArrayList;

/**
 * The type Packet receive listener.
 */
public class PacketReceiveListener implements EventListener {

    private final CardGameServer cardGameServer;

    /**
     * Instantiates a new Packet receive listener.
     *
     * @param cardGameServer the card game server
     */
    public PacketReceiveListener(CardGameServer cardGameServer) {
        this.cardGameServer = cardGameServer;
    }

    /**
     * On login.
     *
     * @param event the event
     */
    @EventHandler
    public void onLogin(LoginEvent event) {
        System.out.println("A new client joined! Welcome, " + event.getSender() + "!");
        this.cardGameServer.addPlayer(event.getSender());
    }

    /**
     * On message receive.
     *
     * @param event the event
     */
    @EventHandler
    public void onMessageReceive(MessageReceivedEvent event) {
        cardGameServer.getNetworkingServer()
            .broadcastPacket(new MessagePacket(event.getSender(), event.getMessage()));
    }

    /**
     * On request user.
     *
     * @param event the event
     */
    @EventHandler
    public void onRequestUser(RequestUserEvent event) {
        cardGameServer.getNetworkingServer().broadcastPacket(
            new UsersPacket("SERVER", new ArrayList<>(cardGameServer.getPlayers())));
    }
}
