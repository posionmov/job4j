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
    /*
    public String[] remove(String[] array) {
        int iterations = 0;
        String tmp;
        for (int i = 0; i != array.length - 2; i++) {
            for (int j = i + 1; j != array.length - 1; j++) {
                if (array[j].equals(array[i])) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    iterations++;
                }
            }
        }
        return Arrays.copyOf(array, iterations - 1);
    }

    public String[] removing(String[] array) {
        String tmp;
        int iter = 0;
        for (int i = 0; i != array.length - 1; i++) {
            for (int j = i + 1; j != array.length; j++) {
                if (!array[j].equals("forRemoving")) {
                    if (array[i].equals(array[j])) {
                        array[j] = "forRemoving";
                    }
                }
                if (j == array.length - 1 && !array[j].equals("forRemoving")) {
                    for (int index = j - 1; index > 0; index--) {
                        if (array[index].equals("forRemoving")) {
                            tmp = array[index];
                            array[index] = array[j];
                            array[j] = tmp;
                            break;
                        }
                    }
                }
            }

        }
        for (int i = 0; i != array.length; i++) {
            if (!array[i].equals("forRemoving")) {
                iter++;
            }
            if (array[i].equals("forRemoving")) {
                for (int j = i + 1; j != array.length; j++) {
                    if (!array[j].equals("forRemoving")) {
                        tmp = array[i];
                        array[i] = array[j];
                        array[j] = tmp;
                        iter++;
                        break;
                    }
                }
            }
        }
        return Arrays.copyOf(array, iter);
    }
    */
    public String[] removing(String[] array) {
        int unic = array.length - 1;
        String tmp;
        for (int i = 0; i != array.length - 1; i++) {
            for (int j = i + 1; j != array.length; j++) {
                if (array[i].equals(array[j])) {
                    tmp = array[j];
                    array[j] = array[unic];
                    array[unic] = tmp;
                    --unic;
                    --j;
                }
            }
        }
        return Arrays.copyOf(array, unic + 1);
    }
}
