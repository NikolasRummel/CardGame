package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import de.aesettlingen.cardgame.gameclient.client_facades.GameFacade;
import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardsPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class GamePanel extends JPanel {

    private final String path = "src/main/resources/images/Table_with_background_without_the_last_Chair_9_to_8.png";
    private final Image backgroundImage = new ImageIcon(path).getImage();
    private final CardsPanel cardsPanel;
    private final GameFacade gameFacade;

    public GamePanel(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
        this.cardsPanel = new CardsPanel(gameFacade.getPlayer().getHand(), gameFacade.getSelectCardMethod());

        Dimension size = new Dimension(810, 720);
        this.setPreferredSize(size);
        this.setSize(size);

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(cardsPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        this.repaint();
    }

    {
        setOpaque(false);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        GraphicsDrawer.drawBackgroundImage(backgroundImage, graphics, this);

        super.paintComponent(graphics);
    }
}