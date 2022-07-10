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

/**
 * The type Networking server.
 */
public class NetworkingServer {

    private ArrayList<ServerPacketListener> clientHandlers;
    private SimpleDateFormat dateFormat;
    private int port;
    private boolean keepGoing;

    /**
     * The Unique id.
     */
    public int uniqueId;

    private final EventBus eventBus;

    /**
     * Instantiates a new Networking server.
     *
     * @param port the port
     */
    public NetworkingServer(int port) {
        this.port = port;
        this.dateFormat = new SimpleDateFormat("HH:mm:ss");
        this.clientHandlers = new ArrayList<>();
        this.eventBus = new DefaultEventBus();
    }

    /**
     * Starts the server and waits for clients to connect
     */
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

    /**
     * Logging
     *
     * @param message the message
     */
    public void info(String message) {
        System.out.println("[" + getDate() + "] " + message);
    }

    /**
     * Sends packet.
     *
     * @param handler the handler
     * @param packet  the packet
     */
    public void sendPacket(ServerPacketListener handler, AbstractPacket packet) {
        try {
            handler.getOutputStream().writeObject(packet);
        } catch (IOException e) {
            System.out.println("Error while sending packet! Reason: " + e);
        }
    }

    /**
     * Broadcasts a packet.
     *
     * @param packet the packet
     */
    public void broadcastPacket(AbstractPacket packet) {
        this.clientHandlers.forEach(
            handler -> System.out.println(handler.getName() + " will get a broadcast Packet"));
        this.clientHandlers.forEach(handler -> sendPacket(handler, packet));
    }

    /**
     * Stops the server
     */
    public void stop() {
        keepGoing = false;
        System.exit(0);
    }

    private String getDate() {
        return dateFormat.format(new Date());
    }

    /**
     * Gets client handlers.
     *
     * @return the client handlers
     */
    public ArrayList<ServerPacketListener> getClientHandlers() {
        return clientHandlers;
    }

    /**
     * Sets client handlers.
     *
     * @param clientHandlers the client handlers
     */
    public void setClientHandlers(ArrayList<ServerPacketListener> clientHandlers) {
        this.clientHandlers = clientHandlers;
    }

    /**
     * Gets date format.
     *
     * @return the date format
     */
    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    /**
     * Sets date format.
     *
     * @param dateFormat the date format
     */
    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * Sets port.
     *
     * @param port the port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Is keep going boolean.
     *
     * @return the boolean
     */
    public boolean isKeepGoing() {
        return keepGoing;
    }

    /**
     * Sets keep going.
     *
     * @param keepGoing the keep going
     */
    public void setKeepGoing(boolean keepGoing) {
        this.keepGoing = keepGoing;
    }

    /**
     * Gets unique id.
     *
     * @return the unique id
     */
    public int getUniqueId() {
        return uniqueId;
    }

    /**
     * Sets unique id.
     *
     * @param uniqueId the unique id
     */
    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    /**
     * Gets event bus.
     *
     * @return the event bus
     */
    public EventBus getEventBus() {
        return eventBus;
    }
}
