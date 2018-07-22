package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing Turn.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class TurnTest {

    /**
     * Test for even number of objects in the array
     */
    @Test
    public void when4And1And6And2Then2And6And1And4() {
        Turn turn = new Turn();
        int[] input = new int[] {4, 1, 6, 2};
        int[] expect = new int[] {2, 6, 1, 4};
        int[] result = turn.turn(input);
        assertThat(result, is(expect));
    }

    /**
     * Test for odd number of objects in the array
     */
    @Test
    public void when12345Then54321() {
        Turn turn = new Turn();
        int[] input = new int[] {1, 2, 3, 4, 5};
        int[] expect = new int[] {5, 4, 3, 2, 1};
        int[] result = turn.turn(input);
        assertThat(result, is(expect));
    }
}
