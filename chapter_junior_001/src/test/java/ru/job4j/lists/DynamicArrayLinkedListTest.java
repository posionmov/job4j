package ru.job4j.lists;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import org.junit.Before;
import java.util.ConcurrentModificationException;
import java.util.Iterator;

/**
 * Класс для тестирования класса DynamicArrayLinkedList.java
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.1
 */
public class DynamicArrayLinkedListTest {

    /**
     * Внутреннее поле теста
     * Содержит ссылку на обьект класса DynamicArrayLinkedList
     */
    private DynamicArrayLinkedList<String> array;

    /**
     * Метод, выполняющийся до стестов
     * Инициализирует обьект класса DynamicArrayLinkedList
     * Так же задает три начальных значения для его полей
     */
    @Before
    public void beforeTests() {
        this.array = new DynamicArrayLinkedList<>();
        this.array.add("one");
        this.array.add("two");
        this.array.add("three");
    }

    /**
     * Тест на получение элементов из списка
     */
    @Test
    public void whenAddThreeItemsThenCanGetThey() {
        assertThat(this.array.get(0), is("one"));
        assertThat(this.array.get(1), is("two"));
        assertThat(this.array.get(2), is("three"));
    }

    /**
     * Тест на получение ошибки при добавлении элемента после создания итератора
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeArrayAfterCreateIteratorThenException() {
        Iterator<String> iterator = this.array.iterator();
        this.array.add("four");
        assertThat(this.array.get(4), is("four"));
        iterator.next();
    }

    /**
     * Тест итератора
     */
    @Test
    public void whenCreateIteratorThenCanGetNext() {
        Iterator<String> iterator = this.array.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Тест если добавить пустой массив
     * Показывает, что ошибка не вылетает
     */
    @Test
    public void whenCreateEmptyArrayThen() {
        DynamicArrayLinkedList<String> array = new DynamicArrayLinkedList<>();
        array.delete(0);
    }

}