package de.aesettlingen.cardgame.logic;

import de.aesettlingen.cardgame.logic.card.CardHand;

import java.util.ArrayList;

public interface GameState {
	String nameOfCurrentPlayer();
	ArrayList<String> listOfPlayerNames();
	CardHand hand();

	boolean hasSomeOneWon();

	ArrayList<String> getNamesOfWinners();
}
