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
 * @since 23.08.2018
 * @version 1.3
 */
public class DynamicArrayLinkedListTest {

    /**
     * Приватный класс, наледующий класс Thread
     * будет использоваться как поток
     * @param <E> любой класс
     */
    private class ThreadClass<E> extends Thread {

        /**
         * Содержит приватные поля:
         * - ссылка на массив
         * - значение потока (его имя)
         */
        private final DynamicArrayLinkedList<E> array;
        private final int value;

        /**
         * Конструктор класса
         * @param array - ссылка на массив
         * @param value - значение (имя) потока
         */
        private ThreadClass(DynamicArrayLinkedList<E> array, int value) {
            this.array = array;
            this.value = value;
        }

        /**
         * Переопределенный метод класса Thread
         * Работает как поток
         */
        @Override
        public void run() {
            System.out.println("Thread " + this.value + "get - " + this.array.get(this.array.getLength() - 1));
            this.array.delete(array.getLength() - 1);
            System.out.println("Thread " + this.value + "was do delete, now Length is " + this.array.getLength());
        }
    }

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
        this.array.delete(0);
    }
    /**
     * Тест на удаление элементов
     */
    @Test
    public void whenDeleteThenChangeLength() {
        this.array.delete(0);
        assertThat(this.array.getLength(), is(2));
        assertThat(this.array.get(0), is("two"));
        this.array.delete(0);
        assertThat(this.array.getLength(), is(1));
        assertThat(this.array.get(0), is("three"));
        this.array.delete(0);
        assertThat(this.array.getLength(), is(0));
    }

    /**
     * Тест на проверку многопоточности
     */
    @Test
    public void whenStart3ThreadsThenDeleteAllItems() throws InterruptedException {
        for (int i = 1; i < 4; i++) {
            ThreadClass<String> thread = new ThreadClass<>(this.array, i);
            thread.start();
        }
        Thread.sleep(1000);
        assertThat(this.array.getLength(), is(0));
    }
}