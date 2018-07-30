package ru.job4j.pseudo;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
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
     * Содержит дефолтный вывод в консоль и Буфер (для результата)
     */
    private final PrintStream stdout = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Methods before test is begin
     */
    @Before
    public void loadOutput() {
        System.out.println("execute before method");
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Methods after test is begin
     */
    @After
    public void backOutput() {
        System.setOut(this.stdout);
        System.out.println("execute after method");
    }

    /**
     * Test for drawing square in terminal
     */
    @Test
    public void whenDrawSquareThenSquareIsDrawing() {
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
        new Paint().draw(new Triangle());
        assertThat(new String(out.toByteArray()), is(new StringBuilder()
                .append("   *   ")
                .append("  * *  ")
                .append(" *   * ")
                .append("*******").append(System.lineSeparator()).toString()));
        System.setOut(stdout);
    }
}
