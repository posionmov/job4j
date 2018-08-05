package ru.job4j.lists;

import java.util.List;

/**
 * Класс для превращения коллекции в двумерный массив (точнее в матрицу)
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
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
        int curI = 0;
        int curJ = 0;
        while (list.size() % rows != 0) {
            list.add(0);
        }
        for (int item : list) {
            result[curI][curJ++] = item;
            if (curJ == rows) {
                curJ = 0;
                curI++;
            }
        }
        return result;
    }
}
