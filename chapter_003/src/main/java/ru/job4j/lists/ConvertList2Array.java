package ru.job4j.lists;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для превращения коллекции в двумерный массив (точнее в матрицу)
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.2
 */
public class ConvertList2Array {

    /**
     * Метод для превращения коллекции в двумерный массив (матрицу)
     * @param list - входящая коллекция
     * @param rows - количество
     * @return двумерный массив из коллекции
     */
    public int[][] convertListToArray(List<Integer> list, int rows) {
        int[][] result = new int[rows][rows];
        int curPosition = 0;
        for (int i = 0; i != rows; i++) {
            for (int j = 0; j != rows; j++) {
                if (curPosition != list.size()) {
                    result[i][j] = list.get(curPosition++);
                } else {
                    break;
                }
            }
        }
        return result;
    }

    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] i : list) {
            for (int j : i) {
                result.add(j);
            }
        }
        return result;
    }
}
