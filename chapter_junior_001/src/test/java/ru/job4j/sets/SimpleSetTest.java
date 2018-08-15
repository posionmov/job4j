package ru.job4j.sets;

import org.junit.Test;
import org.junit.Before;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс, проверяющий работоспособность класса SimpleSet, имитирующего работу сетов
 * @author Galanov Sergey
 * @since 15.08.2018
 * @version 1.0
 */
public class SimpleSetTest {

    /**
     * Внутренне поле класса
     * Содержит только ссылку на обьект класса SimpleSet
     */
    private SimpleSet<String> set;

    /**
     * Методы, вызываемый до тестов
     * Инициализирует обьект класса SimpleSet и задает начальные значения
     */
    @Before
    public void beforeTests() {
        this.set = new SimpleSet<>();
        this.set.add("one");
        this.set.add("two");
        this.set.add("three");
    }

    /**
     * Тест на добавление повторного значеня в имитацию сета
     */
    @Test
    public void whenAddSameElementThenDontAddIt() {
        this.set.add("one");
        assertThat(this.set.getSize(), is(3));
    }

    /**
     * Тест на добавление пвторяющихся значений (а так же иногда и уникальных, т.е. в перемешку)
     * значений в имитацию сета
     */
    @Test
    public void whenAddMoreSameElementsThenDontAddThey() {
        this.set.add("one");
        this.set.add("two");
        this.set.add("three");
        this.set.add("unic1");
        this.set.add("one");
        this.set.add("two");
        this.set.add("three");
        this.set.add("unic1");
        this.set.add("unic2");
        assertThat(this.set.getSize(), is(5));
    }

    /**
     * Тест итератора
     */
    @Test
    public void whenCreatIteratorThenCanGetAllElements() {
        Iterator<String> iterator = this.set.iterator();
        assertThat(iterator.next(), is("one"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("two"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("three"));
        assertThat(iterator.hasNext(), is(false));
    }

    /**
     * Тест на выкидывание исключения итератором при изменении имитации сета после создания итератора
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeSetAfterCreateIteratorThecCatchException() {
        Iterator<String> iterator = this.set.iterator();
        this.set.add("four");
        iterator.next();
    }
}