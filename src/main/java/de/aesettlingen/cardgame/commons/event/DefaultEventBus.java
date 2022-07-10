package de.aesettlingen.cardgame.commons.event;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * The type Default event bus.
 *
 * @author Nikolas Rummel
 * @since 18.05.22
 */
public class DefaultEventBus implements EventBus {

    private final Map<Class<? extends Event>, List<RegisteredHandler>> listeners;
    private final ExecutorService service = Executors.newCachedThreadPool();

    /**
     * Instantiates a new Default event bus.
     */
    public DefaultEventBus() {
        this.listeners = new HashMap<>();
    }

    /**
     * @param listener
     */
    @Override
    public void registerListener(Object listener) {
        for (Method method : listener.getClass().getDeclaredMethods()) {
            final EventHandler handler = method.getAnnotation(EventHandler.class);

            if (handler == null) {
                continue;
            }

            if (method.getParameterCount() != 1) {
                continue;
            }

            this.registerHandler(listener, method, handler);
        }
    }

    /**
     *
     * @param listener
     */
    @Override
    public void unregisterListener(Object listener) {
        this.listeners.values()
            .forEach(listeners -> listeners.removeIf(l -> l.listener.equals(listener)));
    }

    /**
     *
     * @param listener
     * @param method
     * @param handler
     */
    @SuppressWarnings("unchecked")
    private void registerHandler(Object listener, Method method, EventHandler handler) {
        if (handler.handles().length == 0) {
            if (method.getParameterCount() == 1) {
                this.register((Class<? extends Event>) method.getParameterTypes()[0], listener,
                    method, handler);
            }
        }

        for (Class<? extends Event> clazz : handler.handles()) {
            this.register(clazz, listener, method, handler);
        }

        this.sortListeners();
    }

    /**
     *
     * @param clazz
     * @param listener
     * @param method
     * @param handler
     */
    private void register(Class<? extends Event> clazz, Object listener, Method method,
        EventHandler handler) {
        if (!this.listeners.containsKey(clazz)) {
            this.listeners.put(clazz, new LinkedList<>());
        }

        this.listeners
            .get(clazz)
            .add(
                new RegisteredHandler(
                    method,
                    listener,
                    handler.async(),
                    handler.priority()
                )
            );
    }

    /**
     *  Sorts the listeners by their priority
     *  The highest will be called first
     */
    private void sortListeners() {
        for (List<RegisteredHandler> handlers : this.listeners.values()) {
            handlers.sort(
                Comparator.comparingInt(handler -> handler.priority)
            );
        }
    }

    /**
     * Triggers the event
     *
     * @param event
     */
    @Override
    public void callEvent(Event event) {
        final List<RegisteredHandler> list = this.listeners.get(event.getClass());

        if (list == null || list.isEmpty()) {
            return;
        }

        list.forEach(handler -> {
            if (handler.isAsync()) {
                this.service.submit(() -> handler.invoke(event));
                return;
            }

            handler.invoke(event);
        });
    }

    /**
     *
     * @param event
     * @param eventConsumer
     */
    @Override
    public void callEventAndExecuteIfNotCanceled(CancelableEvent event,
        Consumer<CancelableEvent> eventConsumer) {
        this.callEvent(event);

        if (!event.isCanceled()) {
            eventConsumer.accept(event);
        }
    }

    private static class RegisteredHandler {

        private final Method method;
        private final Object listener;


        private final boolean async;
        private final int priority;

        /**
         * Instantiates a new Registered handler.
         *
         * @param method   the method
         * @param listener the listener
         * @param async    the async
         * @param priority the priority
         */
        public RegisteredHandler(Method method, Object listener, boolean async, int priority) {
            this.method = method;
            this.listener = listener;
            this.async = async;
            this.priority = priority;
        }

        /**
         * Invoke.
         *
         * @param event the event
         */
        public void invoke(Event event) {
            try {
                this.method.invoke(listener, event);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                System.err.println("Could not fire event: " + e.getMessage());
            }
        }

        /**
         * Is async boolean.
         *
         * @return the boolean
         */
        public boolean isAsync() {
            return async;
        }
    }
}