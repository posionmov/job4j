package ru.job4j.array;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing BubbleSort.java
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class BubbleSortTest {

    /**
     * Func for testing "sort"
     */
    @Test
    public void when51273Then12357() {
        BubbleSort bubble = new BubbleSort();
        int[] input = new int[] {5, 1, 2, 7, 3};
        int[] expect = new int[] {1, 2, 3, 5, 7};
        int[] result = bubble.sort(input);
        assertThat(result, is(expect));
    }
}
