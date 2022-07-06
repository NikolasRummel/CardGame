package de.aesettlingen.cardgame.commons.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.bind.TypeAdapters;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;
import java.util.UUID;

/**
 * @author Nikolas Rummel
 * @since 06.07.22
 */
public class JsonDocument implements IDocument<JsonDocument> {

    public static final Gson GSON = new GsonBuilder()
        .registerTypeAdapter(UUID.class, TypeAdapters.UUID)
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create();

    protected final JsonObject jsonObject;

    public JsonDocument(JsonObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JsonDocument(JsonElement jsonElement) {
        this(jsonElement.isJsonObject() ? jsonElement.getAsJsonObject() : new JsonObject());
    }

    public JsonDocument() {
        this(new JsonObject());
    }

    public JsonDocument(Object object) {
        this(GSON.toJsonTree(object));
    }

    public static JsonDocument createDocument() {
        return new JsonDocument();
    }

    public static JsonDocument createDocument(Object object) {
        return new JsonDocument(GSON.toJsonTree(object));
    }

    public static JsonDocument createDocument(String json) {
        try {
            return new JsonDocument(JsonParser.parseString(json));
        } catch (JsonSyntaxException exception) {
            exception.printStackTrace();
            return new JsonDocument();
        }
    }

    @Override
    public Collection<String> keys() {
        Collection<String> collection = new ArrayList<>(this.jsonObject.size());

        for (Map.Entry<String, JsonElement> entry : this.jsonObject.entrySet()) {
            collection.add(entry.getKey());
        }

        return collection;
    }

    @Override
    public int size() {
        return this.jsonObject.size();
    }

    @Override
    public JsonDocument clear() {
        for (Map.Entry<String, JsonElement> elementEntry : this.jsonObject.entrySet()) {
            this.jsonObject.remove(elementEntry.getKey());
        }
        return this;
    }

    @Override
    public JsonDocument remove(String key) {
        this.jsonObject.remove(key);
        return this;
    }

    @Override
    public boolean contains(String key) {
        return key != null && this.jsonObject.has(key);
    }

    @Override
    public <T> T toInstanceOf(Class<T> tClass) {
        return GSON.fromJson(this.jsonObject, tClass);
    }

    @Override
    public <T> T toInstanceOf(Type type) {
        return GSON.fromJson(this.jsonObject, type);
    }

    @Override
    public JsonDocument append(String key, Object value) {
        this.jsonObject.add(key, GSON.toJsonTree(value));
        return this;
    }

    @Override
    public JsonDocument append(String key, String value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }


    @Override
    public JsonDocument append(String key, Number value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    @Override
    public JsonDocument append(String key, Boolean value) {
        this.jsonObject.addProperty(key, value);
        return this;
    }

    @Override
    public JsonDocument append(String key, JsonDocument value) {
        this.jsonObject.add(key, value.jsonObject);
        return this;
    }

    public JsonDocument append(JsonObject jsonObject) {
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            this.jsonObject.add(entry.getKey(), entry.getValue());
        }
        return this;
    }

    @Override
    public JsonDocument append(JsonDocument document) {
        this.append(document.jsonObject);
        return this;
    }

    @Override
    public JsonDocument append(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            this.append(entry.getKey(), entry.getValue());
        }

        return this;
    }

    @Override
    public JsonDocument append(String key, byte[] bytes) {
        return this.append(key, Base64.getEncoder().encodeToString(bytes));
    }

    @Override
    public JsonDocument getDocument(String key) {
        if (!this.contains(key)) {
            return null;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonObject()) {
            return new JsonDocument(jsonElement);
        } else {
            return null;
        }
    }

    @Override
    public int getInt(String key) {
        if (!this.contains(key)) {
            return 0;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsInt();
        } else {
            return 0;
        }
    }

    @Override
    public double getDouble(String key) {
        if (!this.contains(key)) {
            return 0;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsDouble();
        } else {
            return 0;
        }
    }

    @Override
    public float getFloat(String key) {
        if (!this.contains(key)) {
            return 0;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsFloat();
        } else {
            return 0;
        }
    }

    @Override
    public byte getByte(String key) {
        if (!this.contains(key)) {
            return 0;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsByte();
        } else {
            return 0;
        }
    }

    @Override
    public short getShort(String key) {
        if (!this.contains(key)) {
            return 0;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsShort();
        } else {
            return 0;
        }
    }

    @Override
    public long getLong(String key) {
        if (!this.contains(key)) {
            return 0;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsLong();
        } else {
            return 0;
        }
    }

    @Override
    public boolean getBoolean(String key) {
        if (!this.contains(key)) {
            return false;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsBoolean();
        } else {
            return false;
        }
    }


    @Override
    public String getString(String key) {
        if (!this.contains(key)) {
            return null;
        }

        final JsonElement jsonElement = this.jsonObject.get(key);

        if (jsonElement.isJsonPrimitive()) {
            return jsonElement.getAsString();
        } else {
            return null;
        }
    }

    @Override
    public byte[] getBinary(String key) {
        return Base64.getDecoder().decode(this.getString(key));
    }

    @Override
    public <T> T get(String key, Class<T> tClass) {
        return this.get(key, GSON, tClass);
    }

    @Override
    public <T> T get(String key, Type type) {
        return this.get(key, GSON, type);
    }

    public <T> T get(String key, Gson gson, Class<T> tClass) {
        if (key == null || gson == null || tClass == null) {
            return null;
        }

        final JsonElement jsonElement = this.get(key);

        if (jsonElement == null) {
            return null;
        } else {
            return gson.fromJson(jsonElement, tClass);
        }
    }

    public <T> T get(String key, Gson gson, Type type) {
        if (key == null || gson == null || type == null) {
            return null;
        }

        if (!this.contains(key)) {
            return null;
        }

        final JsonElement jsonElement = this.get(key);

        if (jsonElement == null) {
            return null;
        } else {
            return gson.fromJson(jsonElement, type);
        }
    }

    public JsonElement get(String key) {
        if (!this.contains(key)) {
            return null;
        }

        return this.jsonObject.get(key);
    }


}
