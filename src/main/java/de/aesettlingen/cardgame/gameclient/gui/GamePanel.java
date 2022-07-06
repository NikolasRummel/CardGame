package de.aesettlingen.cardgame.gameclient.gui;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Nikolas Rummel
 * @since 06.07.22
 */
public class GamePanel extends JPanel {

    public GamePanel() {
        this.add(new JLabel("CardGame"));
        this.setBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.RED),
                "game gui"
            )
        );

    }

}
