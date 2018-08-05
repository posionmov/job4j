package ru.job4j.lists;

import java.util.List;

/**
 * Класс для превращения коллекции в двумерный массив (точнее в матрицу)
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.1
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
}
