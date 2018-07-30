package ru.job4j.pseudo;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Class for testing Triangle.java
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class TriangleTest {

    /**
     * Test of creating String with Triangle
     */
    @Test
    public void whenDrawThenDrawingTriangle() {
        Triangle triangle = new Triangle();
        assertThat(triangle.draw(), is(new StringBuilder()
                .append("   *   ")
                .append("  * *  ")
                .append(" *   * ")
                .append("*******").toString()));
    }
}
