package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import de.aesettlingen.cardgame.gameclient.client_facades.GameFacade;
import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardImageLabel;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardsPanel;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauMove;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GamePanel extends JPanel {

    private final String path = "src/main/resources/images/Table_with_background_without_the_last_Chair_9_to_8.png";
    private final Image backgroundImage = new ImageIcon(path).getImage();
    private final CardsPanel cardsPanel;
    private final GameFacade gameFacade;

    private CardImageLabel topCardLabel;

    public GamePanel(GameFacade gameFacade) {
        this.gameFacade = gameFacade;
        this.cardsPanel = new CardsPanel(gameFacade.getPlayer().getHand(), gameFacade::sendCardOfMove);

        Dimension size = new Dimension(810, 720);
        this.setPreferredSize(size);
        this.setSize(size);

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(cardsPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // display the back of a card to show the draw pile
        CardImageLabel cardBackLabel = new CardImageLabel(new Card("", ""));
        cardBackLabel.flip();
//        cardBackLabel.setHover(false);
        cardBackLabel.setLocation(350, 300);
        cardBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onRaiseACard();
            }
        });

        this.topCardLabel = new CardImageLabel();
        topCardLabel.setHover(false);
        topCardLabel.setLocation(500, 300);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false); // make JComponent Transparent
        centerPanel.setLayout(null);
        centerPanel.add(cardBackLabel);
        centerPanel.add(topCardLabel);
        this.add(centerPanel, BorderLayout.CENTER);

        updateTopCard();
        this.repaint();
    }

    {
        setOpaque(false);
    }

    private void updateTopCard() {
        this.topCardLabel.setCard(gameFacade.getTopCard());
    }

    private void onRaiseACard() {
          gameFacade.sendCardOfMove(null); // this will let the player raise a card
	    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        GraphicsDrawer.drawBackgroundImage(backgroundImage, graphics, this);

        super.paintComponent(graphics);
    }
}