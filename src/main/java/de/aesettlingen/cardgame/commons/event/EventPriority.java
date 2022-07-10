package de.aesettlingen.cardgame.commons.event;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class EventPriority {

    /**
     * Defines which EventHandlermethod will be called first
     */

    public static final int FIRST = 0;
    public static final int SECOND = 25;
    public static final int DEFAULT = 50;
    public static final int SECOND_LAST = 75;
    public static final int LAST = 100;
}
