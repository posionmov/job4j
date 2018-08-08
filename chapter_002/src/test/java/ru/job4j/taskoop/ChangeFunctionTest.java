package ru.job4j.taskoop;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Класс для тестирования метода выдачи сдачи
 * @author Galanov Sergey
 * @since 08.08.2018
 * @version 1.0
 */
public class ChangeFunctionTest {

    /**
     * Тест на выдачу сдачи при условии, что дали на много большую купюру, чем стоит товар
     */
    @Test
    public void whenBuyAndGive100ThenReceiveCorrectChange() {
        ChangeFunction function = new ChangeFunction();
        int[] result = function.change(100, 22);
        int[] expect = new int[] {10, 10, 10, 10, 10, 10, 10, 5, 2, 1};
        assertThat(result, is(expect));
    }

    /**
     * Тест на выдачу сдачи при условии, что мы дали денег меньше, чем стоит товар
     */
    @Test
    public void whenBuyAndGive20ThenReceiveCorrectChange() {
        ChangeFunction function = new ChangeFunction();
        int[] result = function.change(20, 22);
        int[] expect = new int[] {};
        assertThat(result, is(expect));
    }

    /**
     * Тест на выдачу сдачи при условии, что мы дали сумму денег, приближенную к цене товара
     */
    @Test
    public void whenBuyAndGive33TheReceiveCorrectChange() {
        ChangeFunction function = new ChangeFunction();
        int[] result = function.change(33, 20);
        int[] expect = new int[] {10, 2, 1};
        assertThat(result, is(expect));
    }


}
