package ru.job4j.array;

/**
 * Class for checkig matrix for the same elements diagonally
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.1
 */
public class MatrixCheck {

    /**
     * func for answering "is that array be mono?"
     * @param data - inputing array
     * @return true or false
     */
    public boolean mono(boolean[][] data) {
        boolean isMono = true;
        for (int i = 0; i != data[0].length - 1; i++) {
            if (data[i][i] != data[i + 1][i + 1]) {
                isMono = false;
            }
        }
        return isMono;
    }
}
