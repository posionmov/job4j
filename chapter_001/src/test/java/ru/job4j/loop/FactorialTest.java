package ru.job4j.loop;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Class for testing Factorial.java
 * @author Galanov Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class FactorialTest {

    /**
     * Test func "calc" if number is 5
     * Right answer is 120
     */
    @Test
    public void when5Then120() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(5), is(120));
    }

    /**
     * Test func "calc" if number is 0
     * Right answer is 1
     */
    @Test
    public void when0Then1() {
        Factorial factorial = new Factorial();
        assertThat(factorial.calc(0), is(1));
    }
}
