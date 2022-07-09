package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import javax.swing.*;
import java.awt.*;

public class PlayerLabel extends JLabel {

	private String playerName;
	private boolean hasMovePermission = false;

	private final Color defaultColor = super.getForeground();
	private Color hasPermissionColor = Color.GREEN;

	PlayerLabel() {
		initGuiElements();
		disable();
	}

	PlayerLabel(String name) {
		this.setPlayerName(name);
		initGuiElements();
	}

	@SuppressWarnings("deprecation")
	public void disable() {
		super.setVisible(false);
	}

	@SuppressWarnings("deprecation")
	public void enable() {
		super.setVisible(true);
	}

	public void initGuiElements() {
		super.setName(playerName);
		super.setOpaque(false);
	}

	public boolean setHasMovePermission() {
		return hasMovePermission;
	}

	public void setPermission(boolean hasMovePermission) {
		this.hasMovePermission = hasMovePermission;
		super.setText((hasMovePermission? " " : "") + playerName);
		updateColor();
	}

	public void setPlayerName(String name) {
		this.playerName = name;
		super.setText(name);
	}

	@Override
	public void setText(String text) { }

	public String getPlayerName() {
		return this.playerName;
	}

	public void updateColor() {
		super.setForeground(hasMovePermission? hasPermissionColor: defaultColor);
	}

	public Color getHasPermissionColor() {
		return this.hasPermissionColor;
	}

	public void setHasPermissionColor(Color color) {
		this.hasPermissionColor = color;
		updateColor();
	}
}