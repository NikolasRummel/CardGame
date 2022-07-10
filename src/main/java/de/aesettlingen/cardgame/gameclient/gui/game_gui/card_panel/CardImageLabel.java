package de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel;

import de.aesettlingen.cardgame.gameclient.gui.CardToImageMapper;
import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.logic.card.Card;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The type Card image label.
 */
public class CardImageLabel extends JLabel {
    private Card card;

    private final int defaultLabelWidth = 97;
    private final int defaultLabelHeight = 160;

    private final int labelWidth;
    private final int labelHeight;

    private Color hoverColor = UIManager.getColor("hoverColor");

    private boolean isFlipped = false;
    private boolean isHoverEnabled = true;

    /**
     * Instantiates a new Card image label.
     */
    public CardImageLabel() {
        this.labelHeight = defaultLabelHeight;
        this.labelWidth = defaultLabelWidth;
        initGuiElements();
    }

    /**
     * Instantiates a new Card image label.
     *
     * @param card the card
     */
    public CardImageLabel(Card card) {
        this.card = card;
        this.labelHeight = defaultLabelHeight;
        this.labelWidth = defaultLabelWidth;
        initGuiElements();
    }

    /**
     * Instantiates a new Card image label.
     *
     * @param labelWidth  the label width
     * @param labelHeight the label height
     */
    public CardImageLabel(int labelWidth, int labelHeight) {
        this.labelHeight = labelHeight;
        this.labelWidth = labelWidth;
        initGuiElements();
    }

    /**
     * Instantiates a new Card image label.
     *
     * @param card        the card
     * @param labelWidth  the label width
     * @param labelHeight the label height
     */
    public CardImageLabel(Card card, int labelWidth, int labelHeight) {
        this.card = card;
        this.labelHeight = labelHeight;
        this.labelWidth = labelWidth;
        initGuiElements();
    }

    /**
     * Gets card.
     *
     * @return the card
     */
    public Card getCard() {
        return card;
    }

    /**
     * Sets card.
     *
     * @param card the card
     */
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

    /**
     * Flip.
     */
    public void flip() {
        isFlipped =!isFlipped;
    }

    /**
     * Is flipped boolean.
     *
     * @return the boolean
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Is hover enabled boolean.
     *
     * @return the boolean
     */
    public boolean isHoverEnabled() {
        return isHoverEnabled;
    }

    /**
     * Sets hover.
     *
     * @param isHoverEnabled the is hover enabled
     */
    public void setHover(boolean isHoverEnabled) {
        this.isHoverEnabled = isHoverEnabled;
        if (!isHoverEnabled) removeHover();
    }

    /**
     * Sets hover color.
     *
     * @param hoverColor the hover color
     */
    public void setHoverColor(Color hoverColor) {
        this.hoverColor = hoverColor;
    }

    /**
     * Gets hover color.
     *
     * @return the hover color
     */
    public Color getHoverColor() {
        return this.hoverColor;
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (card == null && !isFlipped) return;

        if (!isFlipped)
                GraphicsDrawer.drawBackgroundImage(CardToImageMapper.mapToImage(card), graphics, this);
            else
                GraphicsDrawer.drawBackgroundImage(CardToImageMapper.getBackImage(), graphics, this);
        super.paintComponent(graphics);
    }
}