package ru.job4j.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, реализуюший функцию превращения матрицы (хоть это и не обязательно) в коллекцию
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class ConvertMatrix2List {

    /**
     * Метод превращения матрицы (или двумерного массива) в коллекцию
     * @param array - двумерный массив целых чисел
     * @return колекция целых чисел
     */
    public List<Integer> convertToList(int[][] array) {
        List<Integer> result = new ArrayList<>();
        for (int[] i : array) {
            for (int j : i) {
                result.add(j);
            }
        }
        return result;
    }

}
