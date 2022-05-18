package de.aesettlingen.cardgame.commons.event;

import java.util.function.Consumer;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public interface EventBus {

    void registerListener(Object o);

    void unregisterListener(Object o);

    void callEvent(final Event event);

    void callEventAndExecuteIfNotCanceled(CancelableEvent event,
        Consumer<CancelableEvent> eventConsumer);
}
