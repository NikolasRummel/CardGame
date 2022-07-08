package de.aesettlingen.cardgame.gameclient.gui;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GamePanel extends JPanel {

    private final String path = "src/main/resources/images/Backgrownd_with_Chair.png";
    private final Image backgroundImage = new ImageIcon(path).getImage();

    public GamePanel() {

        File file = new File(path);
        System.out.printf("path: %s exists: %b\n", path, file.exists());


        // some stuff, so you can see the game gui
//        this.add(new JLabel("this is the game gui"));
//        this.setBorder(
//                BorderFactory.createTitledBorder(
//                        BorderFactory.createLineBorder(Color.RED),
//                        "game gui"
//                )
//        );

    }

    {
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        System.out.println("paintComponent");
        graphics.drawImage(backgroundImage, 0, 0, this);
        super.paintComponent(graphics);
    }
}