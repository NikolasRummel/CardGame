package de.aesettlingen.cardgame.commons.event;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class CancelableEvent extends Event {

    private boolean canceled;
}
