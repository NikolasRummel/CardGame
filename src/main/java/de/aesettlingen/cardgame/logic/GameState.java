package de.aesettlingen.cardgame.logic;

import java.util.ArrayList;

public interface GameState {
	String nameOfCurrentPlayer();
	ArrayList<String> listOfPlayerNames();

	boolean hasSomeOneWon();

	ArrayList<String> getNamesOfWinners();
}
