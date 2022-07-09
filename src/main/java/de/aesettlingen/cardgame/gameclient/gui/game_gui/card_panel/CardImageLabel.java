package de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel;

import de.aesettlingen.cardgame.gameclient.gui.CardToImageMapper;
import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.logic.card.Card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CardImageLabel extends JLabel {
    private Card card;

    private final int defaultLabelWidth = 97;
    private final int defaultLabelHeight = 160;

    private final int labelWidth;
    private final int labelHeight;

    private final Color defaultBackground = this.getBackground();
    private final Color hoverColor = Color.RED;
    private Color backgroundColor = defaultBackground;

    private boolean isFliped = false;

    public CardImageLabel() {
        this.labelHeight = defaultLabelHeight;
        this.labelWidth = defaultLabelWidth;
        initGuiElements();
    }

    public CardImageLabel(Card card) {
        this.card = card;
        this.labelHeight = defaultLabelHeight;
        this.labelWidth = defaultLabelWidth;
        initGuiElements();
    }

    public CardImageLabel(int labelWidth, int labelHeight) {
        this.labelHeight = labelHeight;
        this.labelWidth = labelWidth;
        initGuiElements();
    }

    public CardImageLabel(Card card, int labelWidth, int labelHeight) {
        this.card = card;
        this.labelHeight = labelHeight;
        this.labelWidth = labelWidth;
        initGuiElements();
    }

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void resetColor() {
        this.setBackground(backgroundColor);
        setBorder(new LineBorder(backgroundColor, 2));
    }

    private void initGuiElements() {
        this.setPreferredSize(new Dimension(labelWidth, labelHeight));
        this.setSize(new Dimension(labelWidth, labelHeight));
        this.setBackground(backgroundColor);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                backgroundColor = hoverColor;
                resetColor();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                backgroundColor = defaultBackground;
                resetColor();
            }
        });
    }

    public void flip() {
        isFliped=!isFliped;
    }

    public boolean isFliped() {
        return isFliped;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (card == null) return;

        if (!isFliped)
                GraphicsDrawer.drawBackgroundImage(CardToImageMapper.mapToImage(card), graphics, this);
            else
                GraphicsDrawer.drawBackgroundImage(CardToImageMapper.getBackImage(), graphics, this);
        super.paintComponent(graphics);

        this.setBackground(backgroundColor);
    }
}