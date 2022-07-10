package de.aesettlingen.cardgame.gameclient.gui;

import javax.swing.*;
import java.awt.*;

/**
 * The type Graphics drawer.
 */
public class GraphicsDrawer {


    /**
     * Draws the background image.
     *
     * @param image    the image
     * @param graphics the graphics
     * @param observer the observer
     */
    public static void drawBackgroundImage(Image image, Graphics graphics, JComponent observer) {
        graphics.drawImage(
                image,
                0, 0,
                observer.getWidth(), observer.getHeight(),
                observer
        );
    }
}