package ru.job4j.array;

import org.junit.Test;
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
        String[] array = {"Привет", "Мир", "Привет", "Супер", "asd", "Мир", "qwe"};
        String[] result = arrayDuplicate.remove(array);
        String[] expect = {"Привет", "Мир", "Супер", "asd", "qwe"};
        assertThat(result, is(expect));
    }
}
