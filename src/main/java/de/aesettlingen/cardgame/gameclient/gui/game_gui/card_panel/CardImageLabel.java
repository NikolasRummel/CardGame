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

    private Color hoverColor = new Color(0, 150, 255);

    private boolean isFlipped = false;
    private boolean isHoverEnabled = true;

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

    private void hover() {
        super.setBorder(new LineBorder(hoverColor, 2));
    }

    private void removeHover() {
        setBorder(null);
    }

    private void initGuiElements() {
        this.setPreferredSize(new Dimension(labelWidth, labelHeight));
        this.setSize(new Dimension(labelWidth, labelHeight));
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (isHoverEnabled)
                    hover();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                removeHover();
            }
        });
    }

    public void flip() {
        isFlipped =!isFlipped;
    }

    public boolean isFlipped() {
        return isFlipped;
    }

    public boolean isHoverEnabled() {
        return isHoverEnabled;
    }

    public void setHover(boolean isHoverEnabled) {
        this.isHoverEnabled = isHoverEnabled;
        if (!isHoverEnabled) removeHover();
    }

    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    public Color getHoverColor() {
        return this.hoverColor;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (card == null) return;

        if (!isFlipped)
                GraphicsDrawer.drawBackgroundImage(CardToImageMapper.mapToImage(card), graphics, this);
            else
                GraphicsDrawer.drawBackgroundImage(CardToImageMapper.getBackImage(), graphics, this);
        super.paintComponent(graphics);
    }
}