package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for checking MatrixCheck.java
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.1
 */
public class MatrixCheckTest {

    /**
     * test for func "mono" if array is even and mono
     */
    @Test
    public void whenEvenDataMonoThenTrue() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {{true, true, true}, {false, true, true}, {true, false, true}};
        boolean result = check.mono(input);
        boolean expect = true;
        assertThat(result, is(expect));
    }

    /**
     * test func "mono" if array is even and don't mono
     */
    @Test
    public void whenEvenDataNotMonoThenFalse() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {{true, true, true}, {true, false, true}, {false, false, true}};
        boolean result = check.mono(input);
        boolean expect = false;
        assertThat(result, is(expect));
    }

    /**
     * test func "mono" if raay is odd and mono
     */
    @Test
    public void whenOddDataMonoThenTrue() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {{true, false}, {true, true}};
        boolean result = check.mono(input);
        boolean expect = false;
        assertThat(result, is(expect));
    }

    /**
     * test func "mono" if array is odd and don't mono
     */
    @Test
    public void whenOddDataNotMonoThenFalse() {
        MatrixCheck check = new MatrixCheck();
        boolean[][] input = new boolean[][] {{true, true}, {false, false}};
        boolean result = check.mono(input);
        boolean expect = false;
        assertThat(result, is(expect));
    }
}
