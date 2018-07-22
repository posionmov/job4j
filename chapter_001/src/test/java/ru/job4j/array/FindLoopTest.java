package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class fir testing FindLoop.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class FindLoopTest {

    /**
     * test for func "indexOf"
     */
    @Test
    public void when5And10And3Then0() {
        FindLoop findLoop = new FindLoop();
        assertThat(findLoop.indexOf(new int[]{5, 10, 3}, 5), is(0));
    }
}
