package de.aesettlingen.cardgame.logic.card;

/**
 * The type Card.
 */
public class Card {

    private final String name;
    private final String color;
    private int value;

    /**
     * Instantiates a new Card.
     *
     * @param name  the name
     * @param color the color
     * @param value the value
     */
    public Card(String name, String color, int value) {
        this.name = name;
        this.color = color;
        this.value = value;
    }

    /**
     * Instantiates a new Card.
     *
     * @param name  the name
     * @param color the color
     */
    public Card(String name, String color) {
        this.name = name;
        this.color = color;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(int value) {
        this.value = value;
    }
}