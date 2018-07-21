package ru.job4j.loop;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing Counter.java
 * @author Galanov Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class CounterTest {

    /**
     * Test for func "add" in Counter.java
     */
    @Test
    public void when1And10Then30() {
        Counter counter = new Counter();
        assertThat(counter.add(1, 10), is(30));
    }
}
