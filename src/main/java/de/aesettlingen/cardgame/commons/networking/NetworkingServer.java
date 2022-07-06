package de.aesettlingen.cardgame.commons.networking;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */

import de.aesettlingen.cardgame.commons.event.DefaultEventBus;
import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.listener.ServerPacketListener;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NetworkingServer {

    private ArrayList<ServerPacketListener> clientHandlers;
    private SimpleDateFormat dateFormat;
    private int port;
    private boolean keepGoing;

    public int uniqueId;

    private final EventBus eventBus;

    public NetworkingServer(int port) {
        this.port = port;
        this.dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.clientHandlers = new ArrayList<>();
        this.eventBus = new DefaultEventBus();
    }

    public void start() {
        keepGoing = true;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (keepGoing) {
                info("Waiting for new Clients on port " + port + ".");

                Socket socket = serverSocket.accept();
                if (!keepGoing) {
                    break;
                }

                ServerPacketListener handler = new ServerPacketListener(this, socket);
                clientHandlers.add(handler);
                handler.start();

            }
            System.out.println("closing server");

            serverSocket.close();
            clientHandlers.forEach(handler -> {
                try {
                    handler.getInputStream().close();
                    handler.getOutputStream().close();
                    handler.getSocket().close();
                } catch (Exception e) {
                    info("Error while closing server and clients steams!");
                }
            });
        } catch (IOException e) {
            System.out.println(getDate() + "Error creating serversocket! " + e);
        }
    }

    public void info(String message) {
        System.out.println("[" + getDate() + "] " + message);
    }

    public void sendPacket(ServerPacketListener handler, AbstractPacket packet) {
        try {
            handler.getOutputStream().writeObject(packet);
        } catch (IOException e) {
            System.out.println("Error while sending packet! Reason: " + e);
        }
    }

    public void broadcastPacket(AbstractPacket packet) {
        this.clientHandlers.forEach(
            handler -> System.out.println(handler.getName() + " will get a broadcast Packet"));
        this.clientHandlers.forEach(handler -> sendPacket(handler, packet));
    }

    public void stop() {
        keepGoing = false;
        System.exit(0);
    }

    private String getDate() {
        return dateFormat.format(new Date());
    }

    public ArrayList<ServerPacketListener> getClientHandlers() {
        return clientHandlers;
    }

    public void setClientHandlers(ArrayList<ServerPacketListener> clientHandlers) {
        this.clientHandlers = clientHandlers;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isKeepGoing() {
        return keepGoing;
    }

    public void setKeepGoing(boolean keepGoing) {
        this.keepGoing = keepGoing;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
