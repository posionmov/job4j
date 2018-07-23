package ru.job4j.array;

import java.util.Arrays;

/**
 * Class for relete duplicates from array
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.0
 */
public class ArrayDuplicate {

    /**
     * Func for sort String array and remove duplicates
     * @param array
     * @return sorted array without duplicates
     */
    public String[] remove(String[] array) {
        int iterations = 0;
        String tmp;
        for (int i = 0; i != array.length - 2; i++) {
            for (int j = i + 1; j != array.length - 1; j++) {
                if (array[j] == array[i]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    iterations++;
                }
            }
        }
        return Arrays.copyOf(array, iterations - 1);
    }
}
