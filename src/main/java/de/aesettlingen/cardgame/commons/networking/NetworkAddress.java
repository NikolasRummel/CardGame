package de.aesettlingen.cardgame.commons.networking;


/**
 * The type Network address.
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class NetworkAddress {

    private final String hostName;
    private final int port;

    /**
     * Instantiates a new Network address.
     *
     * @param hostName the host name
     * @param port     the port
     */
    public NetworkAddress(String hostName, int port) {
        this.hostName = hostName;
        this.port = port;
    }

    /**
     * Gets host name.
     *
     * @return the host name
     */
    public String getHostName() {
        return this.hostName;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public int getPort() {
        return this.port;
    }


}
