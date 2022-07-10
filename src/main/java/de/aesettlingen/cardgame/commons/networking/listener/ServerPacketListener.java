package de.aesettlingen.cardgame.commons.networking.listener;

import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;
import de.aesettlingen.cardgame.commons.networking.packet.HandShakePacket;
import de.aesettlingen.cardgame.commons.networking.packet.LoginPacket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * The type Server packet listener.
 * This is the entrypoint for messages from a client
 * 
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class ServerPacketListener extends Thread {

    private final NetworkingServer server;
    private final int id;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private final Socket socket;

    private String clientName;

    /**
     * Instantiates a new Server packet listener.
     *
     * @param server the server
     * @param socket the socket
     */
    public ServerPacketListener(NetworkingServer server, Socket socket) {
        this.server = server;
        this.socket = socket;
        this.id = server.uniqueId++;

        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            server.info("Error creating input & output steams or doing new login! " + e);
        }
    }

    @Override
    public void run() {
        boolean keepGoing = true;

        while (keepGoing) {
            try {
                AbstractPacket packet = (AbstractPacket) inputStream.readObject();
                packet.handle(server.getEventBus());

                //inspect for getting the right object
                if (packet instanceof LoginPacket) {
                    LoginPacket loginPacket = (LoginPacket) packet;
                    this.clientName = loginPacket.getSender();
                }

                //handshake
                server.sendPacket(this, new HandShakePacket("SERVER", packet.getHandshakeId()));
                System.out.println("Server CUrrentShakeId: " + packet.getHandshakeId());

            } catch (IOException | ClassNotFoundException e) {
                server.info("Error while reading " + clientName + "'s packets! " + e);
                break;
            }
        }
    }

    /**
     * Gets input stream.
     *
     * @return the input stream
     */
    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    /**
     * Gets output stream.
     *
     * @return the output stream
     */
    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    /**
     * Gets socket.
     *
     * @return the socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    public int getUniqueId() {
        return id;
    }

}
