package ru.job4j.array;

/**
 * Class for lesson about bbble sort
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.1
 */
public class BubbleSort {

    /**
     * Func for sorting array
     * @param array
     * @return sorted array
     */
    public int[] sort(int[] array) {
        int temp;
        boolean isSorted;
        for (int i = 0; i != array.length; i++) {
            isSorted = true;
            for (int j = 1; j != array.length - i; j++) {
                if (array[j - 1] > array[j]) {
                    temp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = temp;
                    isSorted = false;

                }
            }
            if (isSorted) {
                break;
            }
        }
        return array;
    }
}
