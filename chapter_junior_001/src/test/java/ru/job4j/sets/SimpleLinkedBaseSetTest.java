package ru.job4j.sets;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования работы импровизированного сета на основе связанного списка
 * @author Galanov Sergey
 * @since 15.08.2018
 * @version 1.0
 */
public class SimpleLinkedBaseSetTest {

    /**
     * Приватные поля класса
     * Содержит ссылку на обьект класса SimpleLinkedBaseSet
     */
    private SimpleLinkedBaseSet<String> set;

    /**
     * Метод, вызываемый до выполнения тестов
     * Инициализирует обьект класса и задает ему первоначальные значения
     */
    @Before
    public void beforeTests() {
        this.set = new SimpleLinkedBaseSet<>();
        this.set.add("one");
        this.set.add("two");
        this.set.add("three");
    }

    /**
     * Тест на добавление нового элемента, который идентичен уже имеющемуся в имитации сета
     */
    @Test
    public void whenAddNewSameObjectThenSizeIsNorGrow() {
        this.set.add("two");
        assertThat(this.set.getSize(), is(3));
    }

    /**
     * Тест на добавление множества элементов (и уникальных и нет)
     */
    @Test
    public void whenAddMoreElementsThenGetCorrectSie() {
        this.set.add("one");
        this.set.add("1");
        this.set.add("one");
        this.set.add("3");
        this.set.add("1");
        this.set.add("2");
        this.set.add("1");
        assertThat(this.set.getSize(), is(6));
    }

    /**
     * Тестирование итератора
     */
    @Test
    public void whenCreateIteratorThenCanUseHim() {
        Iterator<String> iterator = this.set.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Тестирование итератора на выкидывание исключений
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeSetAfterHeCreateThenCatchException() {
        Iterator<String> iterator = this.set.iterator();
        this.set.add("four");
        iterator.next();
    }

}