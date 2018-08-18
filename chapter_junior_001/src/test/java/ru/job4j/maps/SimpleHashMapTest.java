package ru.job4j.maps;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import org.junit.Test;
import org.junit.Before;

import java.util.Iterator;

/**
 * Класс для тестирования импровизированной мапы
 * @author Galanov Sergey
 * @since 18.08.2018
 * @version 1.1
 */
public class SimpleHashMapTest {

    /**
     * Приватное поле класса
     * Содержит ссылку на экземпляр класса SimpleHashMap, который является импровизированной хэш мапой
     */
    private SimpleHashMap<Integer, String> simpleHashMap;

    /**
     * Метод, отрабатывающий до выполнения тестов
     */
    @Before
    public void beforeTests() {
        this.simpleHashMap = new SimpleHashMap<>();
        this.simpleHashMap.insert(1, "hello1");
        this.simpleHashMap.insert(2, "hello2");
        this.simpleHashMap.insert(3, "hello3");
    }

    /**
     * Тест на коректное получение значний по ключам
     */
    @Test
    public void whenInsertSomeElementThenCanGetValue() {
        assertThat(this.simpleHashMap.getCurLength(), is(3));
        assertThat(this.simpleHashMap.get(1), is("hello1"));
    }

    /**
     * Тест на удаление элемента
     */
    @Test
    public void whenDeleteElementThenSizeIsGrowDown() {
        this.simpleHashMap.delete(2);
        assertThat(this.simpleHashMap.getCurLength(), is(2));
    }

    /**
     * Тест на добавление повторяющихся элементов
     */
    @Test
    public void whenAddSameElementsThenSizeIsNotGrowUp() {
        this.simpleHashMap.insert(1, "hello1");
        this.simpleHashMap.insert(1, "hello2");
        this.simpleHashMap.insert(3, "hello3");
        assertThat(this.simpleHashMap.getCurLength(), is(3));
    }

    /**
     * Тест на добавление множества элементов
     * т.е. тест на динамичность
     */
    @Test
    public void whenInsertMoreElementsThenCurSizeIsGrow() {
        this.simpleHashMap.insert(510, "1");
        this.simpleHashMap.insert(11, "1");
        this.simpleHashMap.insert(520, "1");
        this.simpleHashMap.insert(12, "1");
        this.simpleHashMap.insert(530, "1");
        this.simpleHashMap.insert(13, "1");
        this.simpleHashMap.insert(531, "1");
        this.simpleHashMap.insert(14, "1");
        this.simpleHashMap.insert(532, "1");
        this.simpleHashMap.insert(15, "1");
        this.simpleHashMap.insert(533, "1");
        this.simpleHashMap.insert(16, "1");
        this.simpleHashMap.insert(534, "1");
        this.simpleHashMap.insert(17, "1");
        this.simpleHashMap.insert(535, "1");
        this.simpleHashMap.insert(18, "1");

        assertThat(this.simpleHashMap.getCurLength(), is(16));
    }

    /**
     * Тест итератора
     */
    @Test
    public void whenCreateIteratorThenCanGoNext() {
        Iterator iterator = this.simpleHashMap.iterator();
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("hello1"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("hello2"));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is("hello3"));
        assertThat(iterator.hasNext(), is(false));
    }
}