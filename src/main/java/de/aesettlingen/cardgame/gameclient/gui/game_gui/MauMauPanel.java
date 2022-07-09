package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import de.aesettlingen.cardgame.gameclient.client_facades.MauMauFacade;
import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardImageLabel;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardsPanel;
import de.aesettlingen.cardgame.logic.GameState;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MauMauPanel extends GamePanel {

    private final String path = "src/main/resources/images/Table_with_background_without_the_last_Chair_9_to_8.png";
    private final Image backgroundImage = new ImageIcon(path).getImage();
    private final CardsPanel cardsPanel;
    private final MauMauFacade gameFacade;

    private CardImageLabel topCardLabel;

    private final PlayerLabel[] playerLabels;

    public MauMauPanel(MauMauFacade gameFacade, MauMauState gameState) {
        this.gameFacade = gameFacade;
        this.cardsPanel = new CardsPanel(gameState.hand(), gameFacade::sendCardOfMove);

        Dimension size = new Dimension(810, 720);
        this.setPreferredSize(size);
        this.setSize(size);

        this.setLayout(new BorderLayout());

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add(cardsPanel, BorderLayout.CENTER);
        this.add(bottomPanel, BorderLayout.SOUTH);

        // display the back of a card to show the draw pile
        CardImageLabel cardBackLabel = new CardImageLabel();
        cardBackLabel.flip();
        cardBackLabel.setLocation(350, 300);
        cardBackLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                onRaiseACard();
            }
        });

        this.topCardLabel = new CardImageLabel(gameState.topCard());
        topCardLabel.setHover(false);
        topCardLabel.setLocation(500, 300);

        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false); // make JComponent Transparent
        centerPanel.setLayout(null);
        centerPanel.add(cardBackLabel);
        centerPanel.add(topCardLabel);
        this.add(centerPanel, BorderLayout.CENTER);

        // init playerLabels
        playerLabels = new PlayerLabel[3];
        for (int i = 0; i < playerLabels.length; i++) {
            playerLabels[i] = new PlayerLabel(i < gameState.listOfPlayerNames().size()? gameState.listOfPlayerNames().get(i) : "");
            centerPanel.add(playerLabels[i]);
            if (gameState.nameOfCurrentPlayer().equals(gameFacade.getNameOfPlayer()))
                playerLabels[i].setPermission(true);
        }
        // set positions of PlayerLabels
        // TODO: find right positions for PlayerLabels
        playerLabels[0].setLocation(300, 300);
        playerLabels[0].setLocation(400, 100);
        playerLabels[0].setLocation(500, 300);

        this.repaint();
    }

    {
        setOpaque(false);
    }

    private void update(MauMauState gameState) {
        cardsPanel.update(gameState.hand());
        updatePlayerLabels();
    }

    private void updatePlayerLabels() {
        for (PlayerLabel pl : playerLabels)
            pl.setPermission(pl.getPlayerName().equals(gameFacade.getNameOfPlayer()));
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

    @Override
    public void update(GameState state) {
        update( (MauMauState) state);
    }
}