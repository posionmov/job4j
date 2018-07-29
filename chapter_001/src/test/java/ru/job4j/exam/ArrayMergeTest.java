package ru.job4j.exam;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Class for testing ArrayMerge.java
 * @author Galanov Sergey
 * @since 29.07.2018
 * @version 1.0
 */
public class ArrayMergeTest {

    /**
     * Func for testing if both array is sorted and unic
     */
    @Test
    public void whenTwoArrayThenOneSortedArray() {
    int[] firstArray = new int[] {1, 3, 5};
    int[] secondArray = new int[] {2, 4, 6};
    ArrayMerge arrayMerge = new ArrayMerge();
    int[] result = arrayMerge.mergeArrays(firstArray, secondArray);
    int[] expect = new int[] {1, 2, 3, 4, 5, 6};
    assertThat(result, is(expect));
    }

    /**
     * Func for testing if arrays not unic
     */
    @Test
    public void whenTwoAnotherArraysThenOneSortedArray() {
        int[] first = new int[] {1, 3, 5, 7, 9};
        int[] second = new int[] {1, 2, 3, 4, 6, 8};
        int[] expect = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] result = arrayMerge.mergeArrays(first, second);
        assertThat(result, is(expect));
    }

    /**
     * Test of both arrays is same
     */
    @Test
    public void whenTwoSameArraysThenOneArray() {
        int[] first = new int[] {1, 5, 7, 9, 12};
        int[] same = new int[] {1, 5, 7, 9, 12};
        int[] expect = new int[] {1, 5, 7, 9, 12};
        ArrayMerge arrayMerge = new ArrayMerge();
        int[] result = arrayMerge.mergeArrays(first, same);
        assertThat(result, is(expect));
    }
}
