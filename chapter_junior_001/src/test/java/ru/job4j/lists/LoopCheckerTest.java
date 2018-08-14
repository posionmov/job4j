package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования класса, реализущего алгоритм нахождения циклов
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 */
public class LoopCheckerTest {

    /**
     * Внутренние поля класса
     * Содержит ссылки на экземпляры классов Node и LoopChecker
     */
    private Node<Integer> one;
    private Node<Integer> two;
    private Node<Integer> three;
    private Node<Integer> four;
    private LoopChecker checker;

    /**
     * Метод, выполняющийся до выполнения тестов
     * Инициализирует переменные
     */
    @Before
    public void beforeTests() {
        this.checker = new LoopChecker();
        this.one = new Node<>(1);
        this.two = new Node<>(2);
        this.three = new Node<>(3);
        this.four = new Node<>(4);
    }

    /**
     * Тест на нахождение зацикленности при условии что она в конце
     */
    @Test
    public void whenCreateLoopThenReturnTrue() {
        this.one.nextElement = two;
        this.two.nextElement = three;
        this.three.nextElement = four;
        this.four.nextElement = one;
        assertThat(checker.hasCycle(this.one), is(true));
    }

    /**
     * Тест на нахождение зацикленности при условии что ее нету
     */
    @Test
    public void whenDontCreateLoopThenReturnFalse() {
        this.one.nextElement = two;
        this.two.nextElement = three;
        this.three.nextElement = four;
        this.four.nextElement = null;
        assertThat(checker.hasCycle(this.one), is(false));
    }

    /**
     * Тест на нахождение зациленности при условии что она в середине
     */
    @Test
    public void whenCreateLoopAtTheMiddleThenReturnTrue() {
        this.one.nextElement = two;
        this.two.nextElement = three;
        this.three.nextElement = two;
        this.four.nextElement = null;
        assertThat(checker.hasCycle(this.one), is(true));
    }

}