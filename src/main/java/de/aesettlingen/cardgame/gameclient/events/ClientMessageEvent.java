package de.aesettlingen.cardgame.gameclient.events;

import de.aesettlingen.cardgame.commons.event.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ClientMessageEvent extends Event {

    private String sender;
    private String message;
}
