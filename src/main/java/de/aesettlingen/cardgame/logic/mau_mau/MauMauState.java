package de.aesettlingen.cardgame.logic.mau_mau;

import de.aesettlingen.cardgame.logic.GameState;

import java.util.ArrayList;

record MauMauState(
	String nameOfCurrentPlayer,
	ArrayList<String> listOfPlayerNames,
	ArrayList<Integer> numberOfCards
) implements GameState {}
