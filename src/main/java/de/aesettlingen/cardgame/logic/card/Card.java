package de.aesettlingen.cardgame.logic.card;

public class Card {

    private final String name;
    private final String color;
    private int value;

    public Card(String name, String color, int value) {
        this.name = name;
        this.color = color;
        this.value = value;
    }

    public Card(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}