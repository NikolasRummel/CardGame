package de.aesettlingen.cardgame.logic;

public class Card {
    public void Card(String name, String color, int value) {

    }

    private String name;
    private String color;
    private int value;

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
