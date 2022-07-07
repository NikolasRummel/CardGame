package de.aesettlingen.cardgame.commons.document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.TypeAdapters;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

/**
 * @author Nikolas Rummel
 * @since 06.07.22
 */
public class QuickJsonConfigUtil {

    public final static Gson
        GSON = new GsonBuilder().registerTypeAdapter(UUID.class, TypeAdapters.UUID)
        .disableHtmlEscaping().create();

    public final static Gson PRETTY_GSON = new GsonBuilder()
        .registerTypeAdapter(UUID.class, TypeAdapters.UUID)
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create();

    public static <T> T getFromFile(final File file, Class<T> type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return GSON.fromJson(reader, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T getPrettyFromFile(final File file, Class<T> type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return PRETTY_GSON.fromJson(reader, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void writeToFile(final File file, T t) throws Exception {
        final Writer writer = new OutputStreamWriter(new FileOutputStream(file),
            StandardCharsets.UTF_8);

        GSON.toJson(t, writer);

        writer.flush();
        writer.close();
    }

    public static <T> void writeToFilePretty(final File file, T t) throws Exception {
        final Writer writer = new OutputStreamWriter(new FileOutputStream(file),
            StandardCharsets.UTF_8);

        PRETTY_GSON.toJson(t, writer);

        writer.flush();
        writer.close();
    }
}
