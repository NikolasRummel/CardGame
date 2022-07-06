package de.aesettlingen.cardgame.commons.document;

import java.util.function.Supplier;

/**
 * @author Nikolas Rummel
 * @since 06.07.22
 */
public interface IConfiguration<T> {

    IConfiguration<T> createIfNotExists(final Supplier<T> supplier);

    IConfiguration<T> load();

    IConfiguration<T> write(T type);

    IConfiguration<T> save();

    T get();
}
