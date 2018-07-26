package ru.job4j.array;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test for ArrayDuplicate.java
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.0
 */
public class ArrayDuplicateTest {

    /**
     * Test for func "remove"
     */
    @Test
    public void whenArrayWithDuplicatesThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] array = {"Привет", "Мир", "Привет", "Супер", "asd", "Привет", "Мир", "qwe"};
        String[] result = arrayDuplicate.removing(array);
        String[] expect = {"Привет", "Мир", "Супер", "asd", "qwe"};
        assertThat(Arrays.asList(result), containsInAnyOrder(expect));
    }

    @Test
    public void whenArrayWithTwoDuplicatesThenArrayWithoutDuplicates() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] array = {"Привет", "Мир", "Супер", "Привет", "asd", "Привет", "Привет"};
        String[] result = arrayDuplicate.removing(array);
        String[] expect = {"Привет", "Мир", "Супер", "asd"};
        assertThat(result, is(expect));
    }

    @Test
    public void whenArrayAllOneThenOneItem() {
        ArrayDuplicate arrayDuplicate = new ArrayDuplicate();
        String[] array = {"Привет", "Привет", "Привет", "Привет", "Привет"};
        String[] result = arrayDuplicate.removing(array);
        String[] expect = {"Привет"};
        assertThat(result, is(expect));
    }
}
