package de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel;

import de.aesettlingen.cardgame.gameclient.gui.GraphicsDrawer;
import de.aesettlingen.cardgame.logic.card.Card;
import de.aesettlingen.cardgame.logic.card.CardHand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;

public class CardsPanel extends JPanel {
    private JButton nextButton;
    private JButton previousButton;
    private CardHand cardHand;

    private final int widthOfButtons = 20;
    private final int widthOfCards = 97;
    private final int heightOfCards = 160;
    private final int panelHeight = heightOfCards+10;

    private final int numberOfDisplayedCards = 5;

    private final ArrayList<CardImageLabel> cardLabels;

    private final GraphicsDrawer graphicsDrawer = new GraphicsDrawer();

    private final SelectCardMethod selectCardMethod;

    private final Timer updateTimer = new Timer(200, al -> displayFirstImages());

    public CardsPanel(CardHand cardHand, SelectCardMethod selectCardMethod) {
        this.cardHand = cardHand;
        this.selectCardMethod = selectCardMethod;

        this.setLayout(new BorderLayout());
        Dimension size = new Dimension(2*widthOfButtons+widthOfCards*(numberOfDisplayedCards+1), panelHeight);
        this.setPreferredSize(size);
        this.setSize(size);

        nextButton = new JButton(">");
        nextButton.addActionListener(al -> onNext());
        previousButton = new JButton("<");
        previousButton.addActionListener(al -> onPrevious());

        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout());
        middlePanel.setPreferredSize(new Dimension(widthOfCards*(numberOfDisplayedCards+1), panelHeight));

        this.add(previousButton, BorderLayout.WEST);
        this.add(middlePanel, BorderLayout.CENTER);
        this.add(nextButton, BorderLayout.EAST);

        LinkedList<CardImageLabel> createdLabels = new LinkedList<>();
        for (int i=0; i < numberOfDisplayedCards; i++) {
            CardImageLabel label = new CardImageLabel(widthOfCards, heightOfCards);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    onSelect(label);
                }
            });
            createdLabels.add(label);
            middlePanel.add(label);
        }
        this.cardLabels = new ArrayList<>(createdLabels);
        displayFirstImages();

        this.setSize(new Dimension());

        updateTimer.start();
    }

    private void displayFirstImages() {
        for (int i = 0; i < numberOfDisplayedCards; i++)
            cardLabels.get(i).setCard(i < cardHand.getCards().size()? cardHand.getCards().get(i) : null);

        this.repaint();
    }

    private void onNext() {
        System.out.println("clicked next");
        cardHand.rotate(1);
        displayFirstImages();
    }

    private void onSelect(CardImageLabel cardImageLabel) {
        selectCardMethod.selectCard(cardImageLabel.getCard());
    }

    private void onPrevious() {
        System.out.println("clicked previous");
        System.out.println(cardHand.getCards().toString());
        cardHand.rotate(-1);
        System.out.println(cardHand.getCards().toString());
        displayFirstImages();
    }

    public static void main(String[] args) {
        var f = new JFrame();
        var p = new JPanel();
        f.add(p);

        var hand = new CardHand(
                        new Card("7", "diamonds"),
                        new Card("8", "diamonds"),
                        new Card("9", "diamonds"),
                        new Card("10", "diamonds"),
                        new Card("jack", "diamonds"),
                        new Card("queen", "diamonds"),
                        new Card("king", "diamonds")
                );

        var cp = new CardsPanel(
                hand,
                (Card card) -> {
                    System.out.printf("selected card: %s, %s\n", card.getName(), card.getColor());
                    var success = hand.removeCard(card);
                    System.out.println("succeeded to remove card: " + success);
                }
        );
        p.add(cp);

        f.pack();
        f.setResizable(false);
        f.setVisible(true);
    }
}