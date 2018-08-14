package ru.job4j.lists;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Before;
import org.junit.Test;
import java.util.ConcurrentModificationException;
import java.util.Iterator;


/**
 * Класс для тестирования задания по созданию обертки над массивом и придания ему динамичности
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 */
public class DynamicArrayListTest {

    /**
     * Поле теста
     * Содержит ссылку на обьект класса DynamicArray
     */
    private DynamicArrayList<String> testArray;

    /**
     * Метод, вызывающийся до тестов
     * инициализирует обьект класса DynamicArray и задает ему базовые значения
     */
    @Before
    public void beforeTest() {
        testArray = new DynamicArrayList<>();
        this.testArray.add("one");
        this.testArray.add("two");
        this.testArray.add("three");
    }

    /**
     * Тест на получение значения по индексу
     */
    @Test
    public void whenGetIndexOneThenTwo() {
        assertThat(this.testArray.get(1), is("two"));
    }

    /**
     * Тест на добавлени элемента и получение его по индексу
     * Так же проверяет выбрасывание исключения
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenPutNewElementThenCanGetItAndGetException() {
        Iterator<String> iterator = this.testArray.iterator();
        this.testArray.add("four");
        assertThat(this.testArray.get(3), is("four"));
        iterator.next();
    }

    /**
     * Тест только на выбрасывание исключения
     */
    @Test(expected = ConcurrentModificationException.class)
    public void whenChangeArrayAfterCreateIteratorThenThrowException() {
        Iterator<String> iterator = this.testArray.iterator();
        this.testArray.add("four");
        iterator.next();
    }

    /**
     * Тест, проверяющий динамичность обертки над массивом
     */
    @Test
    public void whenAddMoreElementsThenCanGetLastElement() {
        this.testArray.add("test1");
        this.testArray.add("test2");
        this.testArray.add("test3");
        this.testArray.add("test4");
        this.testArray.add("test5");
        this.testArray.add("test6");
        this.testArray.add("test7");
        this.testArray.add("test8");
        this.testArray.add("test9");
        assertThat(this.testArray.get(11), is("test9"));
    }

}