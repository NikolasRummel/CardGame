package de.aesettlingen.cardgame.commons.networking.listener;

import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;
import de.aesettlingen.cardgame.commons.networking.packet.HandShakePacket;
import de.aesettlingen.cardgame.commons.networking.packet.UsersPacket;
import de.aesettlingen.cardgame.gameclient.CardGameClient;
import java.util.UUID;

/**
 * The type Client
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class ClientPacketListener extends Thread {

    private final NetworkingClient client;
    private int errorCount = 0;
    private long lastError;

    /**
     * Instantiates a new Client packet listener.
     * This is the entrypoint for messages from the server
     *
     * @param client the client
     */
    public ClientPacketListener(NetworkingClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (client.isRunning()) {
            try {
                Object inputObject = client.getInputStream().readObject();

                if (inputObject instanceof AbstractPacket) {
                    AbstractPacket packet = (AbstractPacket) inputObject;
                    packet.handle(client.getEventBus());
                    System.out.println(
                        "Got a new packet:" + inputObject.getClass().getSimpleName());

                    //Users for waitingscreen
                    if(packet instanceof UsersPacket) {
                        UsersPacket usersPacket = (UsersPacket) packet;
                        client.getCardGameClient().getGameGui().getWaitingScreenPanel().setPlayers(usersPacket.getUsers());
                    }

                    //handshake
                    if(packet instanceof HandShakePacket) {
                        UUID incomingHandshakeId = ((HandShakePacket) packet).getHandshakeResponseId();
                        System.out.println("-----Handshake-----");
                        System.out.println(incomingHandshakeId + " ==" + client.getLastSendedHandshakeId());
                        System.out.println("-----Handshake-----");
                        if(incomingHandshakeId.toString().equals(client.getLastSendedHandshakeId().toString())) {
                            System.out.println("Handshake success!");
                        }else {
                            System.out.println("Handshake failed!");
                        }
                    }
                }

            } catch (Exception e) {
                System.out.println("Error with the connection to the server! Reason: " + e);
                client.setRunning(false);
                client.start(); //relogin
                this.errorCount ++;
                this.lastError = System.currentTimeMillis();

                if(errorCount == 10) {

                    //Reset if error is way back in time -> maybe just a short problem
                    long delta = System.currentTimeMillis()-this.lastError;
                    if(delta >= 10*1000) {
                        this.errorCount = 1;
                    }

                    client.setRunning(false);
                    System.err.println("WARNING: Tried 10 times to connect to server, but server is not reachable! ");
                }
            }
        }
    }
}
