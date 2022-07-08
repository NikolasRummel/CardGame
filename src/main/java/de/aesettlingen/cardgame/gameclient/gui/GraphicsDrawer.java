package de.aesettlingen.cardgame.gameclient.gui;

import javax.swing.*;
import java.awt.*;

public class GraphicsDrawer {

    public void drawBackgroundImage(Image image, Graphics graphics, JComponent observer) {
        graphics.drawImage(
                image,
                0, 0,
                observer.getWidth(), observer.getHeight(),
                observer
        );
    }
}