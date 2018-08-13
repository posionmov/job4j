package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестов SimpleArrayList.java
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list; // Ссылка на класс

    /**
     * Метод, который выполняется до выполнения тестов
     * Создает экземпляр класса и заполняет его несколькими значениями
     */
    @Before
    public void beforeTest() {
        list = new SimpleArrayList<>();
        list.add(5);
        list.add(2);
        list.add(3);
    }

    /**
     * Тест поиска по индексу
     */
    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(this.list.get(1), is(2));
    }

    /**
     * Тест получения размера последовательности
     */
    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        assertThat(this.list.getSize(), is(3));
    }

    /**
     * Тест удаления первого элемента
     */
    @Test
    public void whenAddThreeElementsAndDeleteOneGetSizeResultIsTwo() {
        assertThat(this.list.delete(), is(5));
    }

}