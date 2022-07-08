package de.aesettlingen.cardgame.gameclient.gui.game_gui.card_panel;

import de.aesettlingen.cardgame.logic.card.Card;

@FunctionalInterface
public interface SelectCardMethod {
    public void selectCard(Card card);
}
