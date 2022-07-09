package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.GameState;

import java.util.ArrayList;
import java.util.LinkedList;

record MauMauState(
	String nameOfCurrentPlayer,
	ArrayList<String> listOfPlayerNames,
	ArrayList<Integer> numberOfCards
) implements GameState {

	@Override
	public boolean hasSomeOneWon() {
		for (Integer n: numberOfCards)
			if (n == 0) return true;

		return false;
	}

	@Override
	public ArrayList<String> getNamesOfWinners() {
		ArrayList<String> out = new ArrayList<>();
		for (int i = 0; i < listOfPlayerNames.size(); i++)
			if (numberOfCards.get(i) == 0)
				out.add(listOfPlayerNames.get(i));
		return out;
	}
}
