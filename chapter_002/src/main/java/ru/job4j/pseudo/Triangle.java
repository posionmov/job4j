package ru.job4j.pseudo;

/**
 * Class for Triangles
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class Triangle implements Shape {

    /**
     * Overrride (from interface Shape.java) func for creating some String with triangle
     * @return String with triangle
     */
    @Override
    public String draw() {
        StringBuilder result = new StringBuilder();
        result.append("   *   ");
        result.append("  * *  ");
        result.append(" *   * ");
        result.append("*******");
        return result.toString();
    }
}
