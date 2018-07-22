package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing ArrayChar.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class ArrayCharTest {

    /**
     * Test for func "startWith" with True
     */
    @Test
    public void whenHelloAndHeThenTrue() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("He");
        boolean expect = true;
        assertThat(result, is(expect));
    }

    /**
     * Test for func "startWith" with False
     */
    @Test
    public void whenHelloAndHiThenFalse() {
        ArrayChar word = new ArrayChar("Hello");
        boolean result = word.startWith("Hi");
        boolean expect = false;
        assertThat(result, is(expect));
    }
}
