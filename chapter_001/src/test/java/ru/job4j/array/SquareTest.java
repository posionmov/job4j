package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing Square.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class SquareTest {

    /**
     * func for testing "calculate" from Square.java
     */
    @Test
    public void when3Then149() {
        Square square = new Square();
        int[] expect = new int[] {1, 4, 9};
        assertThat(square.calculate(3), is(expect));
    }
}
