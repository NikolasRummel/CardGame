package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.File;

public class GamePanel extends JPanel {

    private final String path = "src/main/resources/images/Table_with_background_without_the_last_Chair_9_to_8.png";
    private final Image backgroundImage = new ImageIcon(path).getImage();

    public GamePanel() {

        this.setPreferredSize(new Dimension(810, 720));

        File file = new File(path);
        System.out.printf("path: %s exists: %b\n", path, file.exists());

        this.repaint();
    }

    {
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
         super.paintComponent(graphics);
        System.out.println("paintComponent");

        System.out.println("width: "+  this.getWidth());
        System.out.println("height: "+ this.getHeight());
        graphics.drawImage(
                backgroundImage,
                0, 0,
                this.getWidth(), this.getHeight(),
                this
        );


        // code to draw a rectangle in order to check the size of things
//        Graphics2D g2=(Graphics2D) graphics;
//        g2.setPaint(Color.PINK);
//        int width = 160;
//        int height = 160;
//
//        int x = this.getWidth()/2-width;
//        int y = this.getHeight()-height;
//        Rectangle2D rect=new Rectangle2D.Double(x,y,width, height);
//        g2.draw(rect);
//        g2.fill(rect);
    }
}