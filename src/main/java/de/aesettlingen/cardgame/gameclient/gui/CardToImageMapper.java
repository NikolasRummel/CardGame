package de.aesettlingen.cardgame.gameclient.gui;

import de.aesettlingen.cardgame.logic.card.Card;

import java.io.File;

public class CardToImageMapper {
    private static String path = "src/main/resources/images/cards/";

    private CardToImageMapper() {}

    public static String getPath() {
        return path;
    }

    public static void setPath(String path) {
        CardToImageMapper.path = path;
        if (CardToImageMapper.path.endsWith("/")) {
            CardToImageMapper.path += "/";
        }
    }

    public static String mapToPath(Card card) {
        return String.format("%s%s/%s_%s.png", path, card.getColor(), card.getName(), card.getColor());
    }

    public static void main(String[] args) {

        Card card = new Card("jack", "hearts");
        String path = CardToImageMapper.mapToPath(card);
        System.out.println("path: "+path);
        File file = new File(path);
        System.out.printf("path: %s exists: %b\n", path, file.exists());
    }
}