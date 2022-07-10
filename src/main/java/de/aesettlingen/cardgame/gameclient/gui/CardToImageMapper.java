package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.logic.card.Card;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * The type Card to image mapper.
 */
public class CardToImageMapper {
    private static String path = "src/main/resources/images/cards/cards_01/";

    private CardToImageMapper() {}

    /**
     * Gets path.
     *
     * @return the path
     */
    public static String getPath() {
        return path;
    }

    /**
     * Sets path.
     *
     * @param path the path
     */
    public static void setPath(String path) {
        CardToImageMapper.path = path;
        if (CardToImageMapper.path.endsWith("/")) {
            CardToImageMapper.path += "/";
        }
    }

    /**
     * Map to icon image icon.
     *
     * @param card the card
     * @return the image icon
     */
    public static ImageIcon mapToIcon(Card card) {
        String path = mapToPath(card);
        return new ImageIcon(path);
    }

    /**
     * Map to image image.
     *
     * @param card the card
     * @return the image
     */
    public static Image mapToImage(Card card) {
        String path = mapToPath(card);
        return new ImageIcon(path).getImage();
    }

    /**
     * Map to path string.
     *
     * @param card the card
     * @return the string
     */
    public static String mapToPath(Card card) {
        return String.format("%s%s/%s_%s.png", path, card.getColor(), card.getName(), card.getColor());
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Card card = new Card("jack", "hearts");
        String path = CardToImageMapper.mapToPath(card);
        System.out.println("path: "+path);
        File file = new File(path);
        System.out.printf("path: %s exists: %b\n", path, file.exists());
    }

    /**
     * Gets back image.
     *
     * @return the back image
     */
    public static Image getBackImage() {
        return new ImageIcon(getBackImagePath()).getImage();
    }

    /**
     * Gets back image path.
     *
     * @return the back image path
     */
    public static String getBackImagePath() {
        return path+"card_back.png";
    }
}