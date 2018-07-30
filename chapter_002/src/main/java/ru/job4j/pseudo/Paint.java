package ru.job4j.pseudo;

/**
 * Class for drawing in terminal some figures
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class Paint {

    /**
     * Func for drawing in terminal
     * @param shape - object of class that he implement Shape interface
     */
    public void draw(Shape shape) {
        System.out.println(shape.draw());
    }
}
