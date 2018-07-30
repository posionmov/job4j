package ru.job4j.pseudo;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing of creating String with square
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class SquareTest {

    /**
     * Test creating String with square
     */
    @Test
    public void whenDrawThenDrawingSquare() {
        Square square = new Square();
        assertThat(square.draw(), is(new StringBuilder()
                .append("*******")
                .append("*     *")
                .append("*     *")
                .append("*******").toString()));
    }
}
