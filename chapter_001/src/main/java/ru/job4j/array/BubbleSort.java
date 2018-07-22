package ru.job4j.array;

/**
 * Class for lesson about bbble sort
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class BubbleSort {

    /**
     * Func for sorting array
     * @param array
     * @return sorted array
     */
    public int[] sort(int[] array) {
        int tmp;
        for (int j = 0; j != array.length; j++) {
            for (int i = 1; i != array.length; i++) {
                if (array[i] < array[i - 1]) {
                    tmp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = tmp;
                }
            }
        }
        return array;
    }
}
