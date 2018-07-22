package ru.job4j.array;

/**
 * Class for checkig matrix for the same elements diagonally
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class MatrixCheck {

    /**
     * func for answering "is that array be mono?"
     * @param data - inputing array
     * @return true or false
     */
    public boolean mono(boolean[][] data) {
        boolean isMono = true;
        for (int row = 0; row != data[0].length - 1; row++) {
            for (int column = 0; column != data[1].length - 1; column++) {
                if (data[row][column] != data[row + 1][column + 1]) {
                    isMono = false;
                }
            }
        }
        return isMono;
    }
}
