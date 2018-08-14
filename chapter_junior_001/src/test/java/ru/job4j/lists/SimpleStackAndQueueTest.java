package ru.job4j.lists;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестов импровизированных Stack и Queue
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 */
public class SimpleStackAndQueueTest {

    /**
     * Приватные поля класса
     */
    private SimpleStack<String> stack; // Ссыка на обьект класса SimpleStack
    private SimpleQueue<String> queue; // Ссылка на обьект класса SimpleQueue

    /**
     * Метод, отрабатывающий до выполнения теста
     * Илиницализирует приватные обьекты класса и задает им начальные значения
     */
    @Before
    public void beforeTests() {
        this.stack = new SimpleStack<>();
        this.stack.push("one");
        this.stack.push("two");
        this.stack.push("three");
        this.queue = new SimpleQueue<>();
        this.queue.push("one");
        this.queue.push("two");
        this.queue.push("three");

    }

    //<------------------------ Test of Stack ------------------------>

    /**
     * Тест корректности возврата из стека (те следованию правилу ЛИФО)
     */
    @Test
    public void whenLastInputThenFirstOutput() {
        assertThat(this.stack.poll(), is("three"));
        assertThat(this.stack.poll(), is("two"));
        assertThat(this.stack.poll(), is("one"));
    }

    /**
     * Тест следованию возврата правилам ЛИФО, а также проверка динамичности массива
     */
    @Test
    public void whenAddMoreElementsThenCanGetAll() {
        this.stack.push("test1");
        this.stack.push("test2");
        this.stack.push("test3");
        this.stack.push("test4");
        this.stack.push("test5");
        this.stack.push("test6");
        this.stack.push("test7");
        this.stack.push("test8");
        this.stack.push("test9");
        this.stack.push("test10");
        assertThat(this.stack.poll(), is("test10"));
        assertThat(this.stack.poll(), is("test9"));
        assertThat(this.stack.poll(), is("test8"));
        assertThat(this.stack.poll(), is("test7"));
        assertThat(this.stack.poll(), is("test6"));
        assertThat(this.stack.poll(), is("test5"));
        assertThat(this.stack.poll(), is("test4"));
        assertThat(this.stack.poll(), is("test3"));
        assertThat(this.stack.poll(), is("test2"));
        assertThat(this.stack.poll(), is("test1"));
        assertThat(this.stack.poll(), is("three"));
        assertThat(this.stack.poll(), is("two"));
        assertThat(this.stack.poll(), is("one"));
    }


    //<------------------------ Test of queue ------------------------>

    /**
     * Тест возврата согласно правилу ФИФО
     */
    @Test
    public void whenFirstInputThenFirstOutput() {
        assertThat(this.queue.poll(), is("one"));
        assertThat(this.queue.poll(), is("two"));
        assertThat(this.queue.poll(), is("three"));
    }

    /**
     * Тест возврата согласно правилу ФИФО, а также тест динамичности массива
     */
    @Test
    public void whenAddMoreElementsThenCanTakeThey() {
        this.queue.push("1");
        this.queue.push("2");
        this.queue.push("3");
        this.queue.push("4");
        this.queue.push("5");
        this.queue.push("6");
        this.queue.push("7");
        this.queue.push("8");
        this.queue.push("9");
        this.queue.push("10");
        assertThat(this.queue.poll(), is("one"));
        assertThat(this.queue.poll(), is("two"));
        assertThat(this.queue.poll(), is("three"));
        assertThat(this.queue.poll(), is("1"));
        assertThat(this.queue.poll(), is("2"));
        assertThat(this.queue.poll(), is("3"));
        assertThat(this.queue.poll(), is("4"));
        assertThat(this.queue.poll(), is("5"));
        assertThat(this.queue.poll(), is("6"));
        assertThat(this.queue.poll(), is("7"));
        assertThat(this.queue.poll(), is("8"));
        assertThat(this.queue.poll(), is("9"));
        assertThat(this.queue.poll(), is("10"));
    }

}