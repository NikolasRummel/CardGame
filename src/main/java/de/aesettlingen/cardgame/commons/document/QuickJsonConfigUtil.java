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
 * The type Quick json config util.
 *
 * @author Nikolas Rummel
 * @since 06.07.22
 */
public class QuickJsonConfigUtil {

    /**
     * The constant GSON.
     */
    public final static Gson
        GSON = new GsonBuilder().registerTypeAdapter(UUID.class, TypeAdapters.UUID)
        .disableHtmlEscaping().create();

    /**
     * The constant PRETTY_GSON.
     */
    public final static Gson PRETTY_GSON = new GsonBuilder()
        .registerTypeAdapter(UUID.class, TypeAdapters.UUID)
        .disableHtmlEscaping()
        .setPrettyPrinting()
        .create();

    /**
     * Gets T from file.
     *
     * @param <T>  the type parameter
     * @param file the file
     * @param type the type
     * @return the from file
     */
    public static <T> T getFromFile(final File file, Class<T> type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return GSON.fromJson(reader, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets T pretty from file.
     *
     * @param <T>  the type parameter
     * @param file the file
     * @param type the type
     * @return the pretty from file
     */
    public static <T> T getPrettyFromFile(final File file, Class<T> type) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            return PRETTY_GSON.fromJson(reader, type);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Write to file.
     *
     * @param <T>  the type parameter
     * @param file the file
     * @param t    the t
     * @throws Exception the exception
     */
    public static <T> void writeToFile(final File file, T t) throws Exception {
        final Writer writer = new OutputStreamWriter(new FileOutputStream(file),
            StandardCharsets.UTF_8);

        GSON.toJson(t, writer);

        writer.flush();
        writer.close();
    }

    /**
     * Write to file pretty.
     *
     * @param <T>  the type parameter
     * @param file the file
     * @param t    the t
     * @throws Exception the exception
     */
    public static <T> void writeToFilePretty(final File file, T t) throws Exception {
        final Writer writer = new OutputStreamWriter(new FileOutputStream(file),
            StandardCharsets.UTF_8);

        PRETTY_GSON.toJson(t, writer);

        writer.flush();
        writer.close();
    }
}
