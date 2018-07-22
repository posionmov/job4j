package ru.job4j.array;

/**
 * Class for creating some Matrix from arrays
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class Matrix {

    /**
     * Func for creating 2d array
     * @param size - size of 2d array
     * @return 2d array
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int row = 0; row != size; row++) {
            for (int column = 0; column != size; column++) {
                table[row][column] = (row + 1) * (column + 1);
            }
        }
        return table;
    }
}
