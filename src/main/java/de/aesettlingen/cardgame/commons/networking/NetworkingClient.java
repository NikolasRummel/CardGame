package de.aesettlingen.cardgame.commons.networking;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.listener.ClientPacketListener;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;
import de.aesettlingen.cardgame.commons.networking.packet.LoginPacket;
import de.aesettlingen.cardgame.gameclient.CardGameClient;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * The type Networking client.
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class NetworkingClient {

    private CardGameClient cardGameClient;
    private boolean running;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;

    private final NetworkAddress address;
    private final String userName;

    private final ClientPacketListener packetListener;
    private final EventBus eventBus;

    private UUID lastSendedHandshakeId;

    /**
     * Instantiates a new Networking client.
     *
     * @param cardGameClient the card game client
     * @param address        the address
     * @param userName       the user name
     * @param eventBus       the event bus
     */
    public NetworkingClient(CardGameClient cardGameClient, NetworkAddress address, String userName, EventBus eventBus) {
        this.cardGameClient = cardGameClient;
        this.address = address;
        this.userName = userName;

        this.packetListener = new ClientPacketListener(this);
        this.eventBus = eventBus;
    }

    /**
     * Start.
     */
    public void start() {
        this.running = true;

        this.openConnection();
        this.startListening();
        this.login();
    }

    /**
     * Send packet.
     *
     * @param packet the packet
     */
    public void sendPacket(AbstractPacket packet) {
        try {
            this.lastSendedHandshakeId = packet.getHandshakeId();
            System.out.println("Curremt HandSHakeID:" + this.lastSendedHandshakeId);
            outputStream.writeObject(packet);
        } catch (IOException e) {
            System.out.println("Error while sending packet! Reason: " + e);
        }
    }

    /**
     * Connects to the server
     */
    private void openConnection() {
        try {
            socket = new Socket(address.getHostName(), address.getPort());
        } catch (Exception e) {
            System.out.println("Error connecting to server! Reason:" + e);
        }

        System.out.println(
            "Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            inputStream = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Exception creating new Input/output Streams: " + e);
        }
    }

    /**
     * Sends the login packet to the server
     */
    private void login() {
        System.out.println("loggin in now...");
        this.sendPacket(new LoginPacket(userName));
    }

    private void startListening() {
        this.packetListener.start();
    }

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        this.running = running;
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
     * Sets input stream.
     *
     * @param inputStream the input stream
     */
    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
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
     * Sets output stream.
     *
     * @param outputStream the output stream
     */
    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
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
     * Sets socket.
     *
     * @param socket the socket
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public NetworkAddress getAddress() {
        return address;
    }

    /**
     * Gets user name.
     *
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Gets packet listener.
     *
     * @return the packet listener
     */
    public ClientPacketListener getPacketListener() {
        return packetListener;
    }

    /**
     * Gets event bus.
     *
     * @return the event bus
     */
    public EventBus getEventBus() {
        return eventBus;
    }

    /**
     * Gets last sended handshake id.
     *
     * @return the last sended handshake id
     */
    public UUID getLastSendedHandshakeId() {
        return lastSendedHandshakeId;
    }

    /**
     * Gets card game client.
     *
     * @return the card game client
     */
    public CardGameClient getCardGameClient() {
        return cardGameClient;
    }
}
