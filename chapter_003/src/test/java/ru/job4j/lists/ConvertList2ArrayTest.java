package ru.job4j.lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестироания ConvertList2Array.java
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.1
 */
public class ConvertList2ArrayTest {

    /**
     * Тест если добавить 7 элеметов, при этом массив 3х3
     */
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.convertListToArray(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7)), 3);
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}};
        assertThat(result, is(expect));
    }

    /**
     * Тест добавления 5 элементов если массив 4х4
     */
    @Test
    public void when5ElementsThen8() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.convertListToArray(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5)), 4);
        int[][] expect = {
                {1, 2, 3, 4},
                {5, 0, 0, 0},
                {0, 0, 0, 0},
                {0, 0, 0, 0}};
        assertThat(result, is(expect));
    }

    /**
     * Тест функции convert, которая должна переводить коллекцию одномерных массивов
     * в коллекцию интеджеров
     */
    @Test
    public void whenAddListsOfArrayThenConvertItToList() {
        ConvertList2Array listConverter = new ConvertList2Array();
        List<int[]> list = new ArrayList<>();
        list.add(new int[]{1, 2});
        list.add(new int[]{3, 4, 5});
        List<Integer> result = listConverter.convert(list);
        List<Integer> expect = Arrays.asList(1, 2, 3, 4, 5);
        assertThat(result, is(expect));
    }
}
