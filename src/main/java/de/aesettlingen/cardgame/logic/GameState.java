package de.aesettlingen.cardgame.logic;

import de.aesettlingen.cardgame.logic.card.CardHand;

import java.util.ArrayList;

/**
 * The interface Game state.
 * Holds all information about each player
 */
public interface GameState {

	/**
	 * Name of current player string.
	 *
	 * @return the string
	 */
	String nameOfCurrentPlayer();

	/**
	 * List of player names array list.
	 *
	 * @return the array list
	 */
	ArrayList<String> listOfPlayerNames();

	/**
	 * Hand card hand.
	 *
	 * @return the card hand
	 */
	CardHand hand();

	/**
	 * Has some one won boolean.
	 *
	 * @return the boolean
	 */
	boolean hasSomeOneWon();

	/**
	 * Gets names of winners.
	 *
	 * @return the names of winners
	 */
	ArrayList<String> getNamesOfWinners();
}
