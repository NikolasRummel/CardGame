package de.aesettlingen.cardgame.commons.networking.listener;

import com.sun.xml.internal.ws.api.message.Packet;
import de.aesettlingen.cardgame.commons.networking.NetworkingServer;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;
import de.aesettlingen.cardgame.commons.networking.packet.LoginPacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class ServerPacketListener extends Thread {

    private NetworkingServer server;
    private int id;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;

    private String clientName;

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

            } catch (IOException | ClassNotFoundException e) {
                server.info("Error while reading " + clientName + "'s packets! " + e);
                break;
            }
        }
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getUniqueId() {
        return id;
    }

}
