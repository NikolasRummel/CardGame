package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import de.aesettlingen.cardgame.gameclient.client_facades.MauMauFacade;
import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardImageLabel;
import de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel.CardsPanel;
import de.aesettlingen.cardgame.logic.GameState;
import de.aesettlingen.cardgame.logic.mau_mau.MauMauState;

import javax.swing.*;
import javax.swing.border.LineBorder;
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

        // init playerLabels
        playerLabels = new PlayerLabel[3];
        for (int i = 0; i < 4; i++) {
            if (i < gameState.listOfPlayerNames().size())
                if (gameFacade.getNameOfPlayer().equals(gameState.listOfPlayerNames().get(i))) {
                    continue;
                }

            int index = i > 0 && playerLabels[i-1] == null? i -1 : i;

            playerLabels[index] = i < gameState.listOfPlayerNames().size()?
              new PlayerLabel(gameState.listOfPlayerNames().get(i), gameState.numberOfCards().get(i)) :
              new PlayerLabel();
            if (gameState.nameOfCurrentPlayer().equals(playerLabels[index].getPlayerName()))
                playerLabels[index].setPermission(true);
        }
        // set positions of PlayerLabels
        // TODO: find right positions for PlayerLabels
        playerLabels[0].setLocation(150, 350);
        playerLabels[1].setLocation(450, 50);
        playerLabels[2].setLocation(750, 350);

        for (PlayerLabel pl: playerLabels) {
            centerPanel.add(pl);
            pl.updateSize();
        }

        System.out.println(gameState.nameOfCurrentPlayer());
        this.add(centerPanel, BorderLayout.CENTER);

        this.repaint();
    }

    {
        setOpaque(false);
    }

    public void update(MauMauState gameState) {
        cardsPanel.update(gameState.hand());
        topCardLabel.setCard(gameState.topCard());
        updatePlayerLabels(gameState.nameOfCurrentPlayer());
        displayPermissionOfThisPlayer(gameState.nameOfCurrentPlayer());
    }

    private void updatePlayerLabels(String nameOfCurrentPlayer) {
        for (PlayerLabel pl : playerLabels) {
            pl.setPermission(pl.getPlayerName().equals(gameFacade.getNameOfPlayer()));
            pl.updateSize();
            pl.setPermission(nameOfCurrentPlayer.equals(pl.getPlayerName()));
        }
    }

    private void displayPermissionOfThisPlayer(String nameOfCurrentPlayer) {
        cardsPanel.setBorder(
          nameOfCurrentPlayer.equals(gameFacade.getNameOfPlayer())?
            new LineBorder(UIManager.getColor("hoverColor")) :
            null
        );
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