package de.aesettlingen.cardgame.commons.networking.listener;

import de.aesettlingen.cardgame.commons.networking.NetworkingClient;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class ClientPacketListener extends Thread {

    private NetworkingClient client;

    public ClientPacketListener(NetworkingClient client) {
        this.client = client;
    }

    @Override
    public void run() {
        while (client.isRunning()) {
            try {
                Object inputObject = client.getInputStream().readObject();

                if(inputObject instanceof AbstractPacket) {
                    AbstractPacket packet = (AbstractPacket) inputObject;
                    packet.handle(client.getEventBus());
                    System.out.println("Got a new packet:" + inputObject.getClass().getSimpleName());
                }

            }catch (Exception e) {
                System.out.println("Error with the connection to the server! Reason: " + e);
            }
        }
    }
}
