package ru.job4j.array;

/**
 * Class for turning arrays
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class Turn {

    /**
     * func for turn array
     * @param array
     * @return turning array
     */
    public int[] turn(int[] array) {
        int temp = 0;
        for (int i = 0; i != (array.length / 2); i++) {
            temp = array[i];
            array[i] = array[array.length - (i + 1)];
            array[array.length - (i + 1)] = temp;
        }
        return array;
    }
}
