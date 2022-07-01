package de.aesettlingen.cardgame.logic;

public class Card {
    private String name;
    private String color;
    private int value;
    public void Card(String name, String color, int value) {
        this.name = name;
        this.color = color;
        this.value = value;
    }

    public void Card(String name, String color) {
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