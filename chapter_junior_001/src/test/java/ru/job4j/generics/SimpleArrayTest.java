package ru.job4j.generics;

import org.junit.Test;
import java.util.Iterator;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для проверки SimpleArray.java
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class SimpleArrayTest {

    /**
     * Метод для тестирования добавления и удаления обьектов из массива
     */
    @Test
    public void whenCreateNewArrayOfStringThenShouldReturnString() {
        SimpleArray<String> array = new SimpleArray<>(10);
        array.add("test1");
        array.add("test2");
        array.set(1, "test3");
        array.delete("test1");
        String result = array.get(0);
        assertThat(result, is("test3"));
    }

    /**
     * Метод для тестирвоания итератора
     */
    @Test
    public void whenCreateNewArrayThenCurrentWorkterator() {
        SimpleArray<String> array = new SimpleArray<String>(10);
        array.add("one");
        array.add("two");
        array.add("three");
        Iterator<String> iterator = array.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(false));
    }

}