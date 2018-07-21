package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing func from Board.java
 * @author Galanov Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class BoardTest {

    @Test
    public void when33Then3x3() {
        Board board = new Board();
        String ln = System.lineSeparator();
        assertThat(board.paint(3, 3), is(String.format("X X%s X %sX X%s", ln, ln, ln)));
    }
}
