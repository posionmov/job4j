package ru.job4j.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * class for testing funtions in Max.java
 */
public class MaxTest {

    /**
     * Test for func "max(int, int)" in Max.java
     */
    @Test
    public void when2And3Then3() {
        Max max = new Max();
        assertThat(max.max(2, 3), is(3));
    }

    /**
     * Test for func "max(int, int, int) if Max.java
     */
    @Test
    public void when2And6And3Then6() {
        Max max = new Max();
        assertThat(max.max(2, 6, 3), is(6));
    }
}
