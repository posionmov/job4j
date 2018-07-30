package ru.job4j.pseudo;

/**
 * Class for Squares
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class Square implements Shape {

    /**
     * Overrride (from interface Shape.java) func for creating some String with Square
     * @return String with Square
     */
    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("*******");
        result.append("*     *");
        result.append("*     *");
        result.append("*******");
        return result.toString();
    }
}
