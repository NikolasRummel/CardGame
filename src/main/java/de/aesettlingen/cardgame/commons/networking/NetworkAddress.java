package de.aesettlingen.cardgame.commons.networking;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */

@Data
@AllArgsConstructor
public class NetworkAddress {

    private String hostName;
    private int port;

}
