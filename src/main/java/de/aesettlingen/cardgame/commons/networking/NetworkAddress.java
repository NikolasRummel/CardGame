package de.aesettlingen.cardgame.commons.networking;


/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */

public class NetworkAddress {

    private String hostName;
    private int port;

    public NetworkAddress(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    public String getHostName() {
        return this.hostName;
    }

    public int getPort() {
        return this.port;
    }


}
