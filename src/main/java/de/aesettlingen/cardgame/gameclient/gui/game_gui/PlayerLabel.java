package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import javax.swing.*;
import java.awt.*;

public class PlayerLabel extends JPanel {

	private String playerName;
	private int numberOfCards;
	private boolean hasMovePermission = false;

	private final Color defaultColor = super.getForeground();
	private JLabel nameLabel = new JLabel();
	private JLabel numberOfCardsLabel = new JLabel();

	private boolean isNumberOfCardsLabelEnabled = true;

	PlayerLabel() {
		initGuiElements();
		disable();
	}

	PlayerLabel(String name) {
		initGuiElements();
		this.setPlayerName(name);
		this.setIsNumberOfCardsLabelEnabled(false);
	}

	PlayerLabel(String name, int numberOfCards) {
		initGuiElements();
		this.setPlayerName(name);
	}

	public void setPlayerName(String name) {
		this.playerName = name;
		this.nameLabel.setText(name);
	}

	public void setNumberOfCardsLabel(int number) {
		numberOfCards = number;
		this.numberOfCardsLabel.setText(String.valueOf(number));
	}

	public void setIsNumberOfCardsLabelEnabled(boolean value) {
		this.isNumberOfCardsLabelEnabled = value;
		this.numberOfCardsLabel.setVisible(value);
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
		super.setOpaque(false);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(nameLabel);
		this.add(numberOfCardsLabel);
		this.setVisible(true);

		this.numberOfCardsLabel.setFont(new Font("Verdana", Font.BOLD, 20));
		this.nameLabel.setFont(new Font("Verdana", Font.BOLD, 20));

		updateSize();
	}

	public void updateSize() {

		int additionalWidthForPermissionDisplay = 0;
		int height = 30;
		int widthPerChar = 30;
		int gap = 5;
		int numberLabelWidth = getLengthOfNullableString(numberOfCardsLabel.getText())*widthPerChar;
		int nameLabelWidth = getLengthOfNullableString(playerName)*widthPerChar;
		if (hasMovePermission)
			numberLabelWidth += additionalWidthForPermissionDisplay;

		Dimension size = new Dimension(nameLabelWidth, height);
		nameLabel.setPreferredSize(size);
		nameLabel.setSize(size);

		size = new Dimension(numberLabelWidth, height);
		numberOfCardsLabel.setPreferredSize(size);
		numberOfCardsLabel.setSize(size);

		size = new Dimension(numberLabelWidth + nameLabelWidth + gap, height);
		super.setPreferredSize(size);
		super.setSize(size);
	}

	private int getLengthOfNullableString(String string) {
		return string == null? 0 : string.length();
	}

	public boolean setHasMovePermission() {
		return hasMovePermission;
	}

	public void setPermission(boolean hasMovePermission) {
		this.hasMovePermission = hasMovePermission;
		this.nameLabel.setText((hasMovePermission? "> " : "") + playerName);
		Color color = hasMovePermission? UIManager.getColor("hasPermissionColor") : UIManager.getColor("text");
		this.nameLabel.setForeground(color);
		this.numberOfCardsLabel.setForeground(color);
	}

	public String getPlayerName() {
		return this.playerName;
	}
}