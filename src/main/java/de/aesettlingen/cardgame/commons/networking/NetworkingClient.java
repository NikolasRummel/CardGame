package de.aesettlingen.cardgame.commons.networking;

import de.aesettlingen.cardgame.commons.event.EventBus;
import de.aesettlingen.cardgame.commons.networking.listener.ClientPacketListener;
import de.aesettlingen.cardgame.commons.networking.packet.AbstractPacket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */

public class NetworkingClient {

    private boolean running;

    private ObjectInputStream inputStream;
    private ObjectOutputStream outputStream;
    private Socket socket;

    private final NetworkAddress address;
    private final String userName;

    private final ClientPacketListener packetListener;
    private final EventBus eventBus;

    public NetworkingClient(NetworkAddress address, String userName, EventBus eventBus) {
        this.address = address;
        this.userName = userName;

        this.packetListener = new ClientPacketListener(this);
        this.eventBus = eventBus;
    }

    public void start() {
        this.running = true;

        this.openConnection();
        this.startListening();
        this.login();
    }

    public void sendPacket(AbstractPacket packet) {
        try {
            outputStream.writeObject(packet);
        } catch (IOException e) {
            System.out.println("Error while sending packet! Reason: " + e);
        }
    }

    private void openConnection() {
        try {
            socket = new Socket(address.getHostName(), address.getPort());
        } catch(Exception e) {
            System.out.println("Error connecting to server! Reason:" + e);
        }

        System.out.println("Connection accepted " + socket.getInetAddress() + ":" + socket.getPort());

        try {
            inputStream  = new ObjectInputStream(socket.getInputStream());
            outputStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Exception creating new Input/output Streams: " + e);
        }
    }

    private void login() {

    }

    private void startListening() {
        this.packetListener.start();
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public NetworkAddress getAddress() {
        return address;
    }

    public String getUserName() {
        return userName;
    }

    public ClientPacketListener getPacketListener() {
        return packetListener;
    }

    public EventBus getEventBus() {
        return eventBus;
    }
}
