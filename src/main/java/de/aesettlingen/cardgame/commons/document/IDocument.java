package de.aesettlingen.cardgame.commons.document;

import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

/**
 * @author Nikolas Rummel
 * @since 06.07.22
 */
public interface IDocument<T extends IDocument<?>> {

    Collection<String> keys();

    int size();

    T clear();

    T remove(String key);

    boolean contains(String key);

    <T> T toInstanceOf(Class<T> clazz);

    <T> T toInstanceOf(Type clazz);

    T append(String key, Object value);

    T append(String key, Number value);

    T append(String key, Boolean value);

    T append(String key, String value);

    T append(String key, T value);

    T append(Map<String, Object> map);

    T append(String key, byte[] bytes);

    T append(T t);

    T getDocument(String key);

    int getInt(String key);

    double getDouble(String key);

    float getFloat(String key);

    byte getByte(String key);

    short getShort(String key);

    long getLong(String key);

    boolean getBoolean(String key);

    String getString(String key);

    byte[] getBinary(String key);

    <T> T get(String key, Class<T> tClass);

    <T> T get(String key, Type type);

    default boolean isEmpty() {
        return this.size() == 0;
    }
}
