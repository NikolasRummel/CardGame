package de.aesettlingen.cardgame.gameclient.gui.game_gui;

import javax.swing.*;
import java.awt.*;

/**
 * The type Player label.
 * Shows the info about the other players
 */
public class PlayerLabel extends JPanel {

	private String playerName;
	private int numberOfCards;
	private boolean hasMovePermission = false;

	private final Color defaultColor = super.getForeground();
	private JLabel nameLabel = new JLabel();
	private JLabel numberOfCardsLabel = new JLabel();

	private boolean isNumberOfCardsLabelEnabled = true;

	/**
	 * Instantiates a new Player label.
	 */
	PlayerLabel() {
		initGuiElements();
		disable();
	}

	/**
	 * Instantiates a new Player label.
	 *
	 * @param name the name
	 */
	PlayerLabel(String name) {
		initGuiElements();
		this.setPlayerName(name);
		this.setIsNumberOfCardsLabelEnabled(false);
	}

	/**
	 * Instantiates a new Player label.
	 *
	 * @param name          the name
	 * @param numberOfCards the number of cards
	 */
	PlayerLabel(String name, int numberOfCards) {
		initGuiElements();
		this.setPlayerName(name);
	}

	/**
	 * Sets player name.
	 *
	 * @param name the name
	 */
	public void setPlayerName(String name) {
		this.playerName = name;
		this.nameLabel.setText(name);
	}

	/**
	 * Sets number of cards label.
	 *
	 * @param number the number
	 */
	public void setNumberOfCardsLabel(int number) {
		numberOfCards = number;
		this.numberOfCardsLabel.setText(String.valueOf(number));
	}

	/**
	 * Sets is number of cards label enabled.
	 *
	 * @param value the value
	 */
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

	/**
	 * Init gui elements.
	 */
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

	/**
	 * Update size.
	 */
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

	/**
	 * Sets has move permission.
	 *
	 * @return the has move permission
	 */
	public boolean setHasMovePermission() {
		return hasMovePermission;
	}

	/**
	 * Sets permission.
	 *
	 * @param hasMovePermission the has move permission
	 */
	public void setPermission(boolean hasMovePermission) {
		this.hasMovePermission = hasMovePermission;
		this.nameLabel.setText((hasMovePermission? "> " : "") + playerName);
		Color color = hasMovePermission? UIManager.getColor("hasPermissionColor") : UIManager.getColor("text");
		this.nameLabel.setForeground(color);
		this.numberOfCardsLabel.setForeground(color);
	}

	/**
	 * Gets player name.
	 *
	 * @return the player name
	 */
	public String getPlayerName() {
		return this.playerName;
	}
}