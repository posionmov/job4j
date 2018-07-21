package ru.job4j.max;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * class for testing funtions in Max.java
 */
public class MaxTest {

    /**
     * Test for func "max" in Max.java
     */
    @Test
    public void when2And3Then3() {
        Max max = new Max();
        assertThat(max.max(2, 3), is(3));
    }
}
