package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test for check.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class CheckTest {

    /**
     * Test for func "mono" if all elements are True
     */
    @Test
    public void whenAllTrueThenTrue() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, true, true};
        boolean result = check.mono(input);
        boolean expect = true;
        assertThat(result, is(expect));
    }

    /**
     * Test for func 'mono" if not all elements are not True
     */
    @Test
    public void whenNotAllTrueThenFalse() {
        Check check = new Check();
        boolean[] input = new boolean[] {true, false, true};
        boolean result = check.mono(input);
        boolean expect = false;
        assertThat(result, is(expect));
    }
}
