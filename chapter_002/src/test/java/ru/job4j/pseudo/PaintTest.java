package ru.job4j.pseudo;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Class for testing Paint.class
 * @author Galanov Sergey
 * @since 30.07.2018
 * @version 1.0
 */
public class PaintTest {

    /**
     * Test for drawing square in terminal
     */
    @Test
    public void whenDrawSquareThenSquareIsDrawing() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Square());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("*******")
                .append("*     *")
                .append("*     *")
                .append("*******").append(System.lineSeparator()).toString()));
        System.setOut(stdout);
    }

    /**
     * Test for drawing triangle in terminal
     */
    @Test
    public void whenDrawTriangleThenTriangleIsDrawing() {
        PrintStream stdout = System.out;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("   *   ")
                .append("  * *  ")
                .append(" *   * ")
                .append("*******").append(System.lineSeparator()).toString()));
        System.setOut(stdout);
    }
}
