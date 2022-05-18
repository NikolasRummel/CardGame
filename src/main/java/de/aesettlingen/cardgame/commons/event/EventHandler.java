package de.aesettlingen.cardgame.commons.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Nikolas Rummel
 * @since 18.05.22
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {

    Class<? extends Event>[] handles() default {};

    boolean async() default false;

    int priority() default EventPriority.DEFAULT;
}
