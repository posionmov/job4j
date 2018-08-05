package ru.job4j.lists;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;
import java.util.List;

/**
 * Класс для тестирования работы ConvertMatrix2List.java
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class ConvertMatrix2ListTest {

    /**
     * Тестировнаие на создание коллекции из матрицы 2х2
     */
    @Test
    public void when2on2ArrayThenList4() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 3},
                {5, 7}};
        List<Integer> expect = Arrays.asList(
                1, 3, 5, 7);
        List<Integer> result = list.convertToList(input);
        assertThat(result, is(expect));
    }

    /**
     * Тексирование на создание коллекции из матрицы 3х3
     */
    @Test
    public void when3on3ArrayThenList6() {
        ConvertMatrix2List list = new ConvertMatrix2List();
        int[][] input = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}};
        List<Integer> expect = Arrays.asList(
                1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> result = list.convertToList(input);
        assertThat(result, is(expect));
    }
}
