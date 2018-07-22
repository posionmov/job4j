package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing Matrix.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class MatrixTest {

    /**
     * Test for func "multiple"
     */
    @Test
    public void when2Then2x2() {
        Matrix matrix = new Matrix();
        int[][] result = matrix.multiple(2);
        int[][] expect = {{1, 2}, {2, 4}};
        assertThat(result, is(expect));
    }
}
