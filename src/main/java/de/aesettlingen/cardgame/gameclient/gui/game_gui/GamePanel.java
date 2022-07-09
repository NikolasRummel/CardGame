package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import de.aesettlingen.cardgame.logic.GameState;

import javax.swing.*;

public abstract class GamePanel extends JPanel {

	public abstract void update(GameState state);
}