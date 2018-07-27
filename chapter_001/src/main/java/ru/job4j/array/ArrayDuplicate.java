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
    public String[] removing(String[] array) {
        int unic = array.length;
        String tmp;
        for (int i = 0; i < unic; i++) {
            for (int j = i + 1; j < unic; j++) {
                if (array[i].equals(array[j])) {
                    array[j] = array[unic - 1];
                    unic--;
                    j--;
                }
            }
        }
        return Arrays.copyOf(array, unic);
    }
}
