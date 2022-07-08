package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import de.aesettlingen.cardgame.gameclient.gui.CardToImageMapper;
import de.aesettlingen.cardgame.logic.card.CardHand;

import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {
    private JButton nextButton;
    private JButton previousButton;
    private CardHand cardHand;

    public CardPanel(CardHand cardHand) {
        this.cardHand = cardHand;
        this.setLayout(new FlowLayout());

        nextButton = new JButton(">");
        nextButton.addActionListener(al -> onNext());
        previousButton = new JButton("<");
        previousButton.addActionListener(al -> onPrevious());

        this.add(previousButton, FlowLayout.LEFT);
        this.add(nextButton, FlowLayout.RIGHT);

//        cardHand.getCards().forEach((Card card) -> this.add(createCardPanel(card)));
        displayFirstImages();

        // TODO:
        this.setSize(new Dimension());
    }

    private void displayFirstImages() {
        displayFirstImages(5);
    }

    private void displayFirstImages(int n) {
        for (int i = 0; i < n; i++) {
            if (cardHand.getCards().size() <= i) break;
            String path = CardToImageMapper.mapToPath(cardHand.getCards().get(i));
            Image image = new ImageIcon(path).getImage();
            drawImage(image, i);
        }
    }

    private void drawImage(Image Image, int index) {

    }

    private void onNext() {
        cardHand.rotate(1);
    }

    private void onPrevious() {
        cardHand.rotate(-1);
    }
}
