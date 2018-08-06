package ru.job4j.exam;
import java.util.Arrays;

/**
 * Class for merge two sorted arrays
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.1
 */
public class ArrayMerge {

    /**
     * Func for merge two sorted arrays
     * @param first - first array if int[]
     * @param second - second array of int[]
     * @return new sorted array of int[]
     */
    public int[] mergeArrays(int[] first, int[] second) {
        int[] result = new int[first.length + second.length];
        int indexFirst = 0;
        int indexSecond = 0;
        int repeat = result.length;
        for (int i = 0; i != result.length; i++) {
            if (indexFirst != first.length && indexSecond != second.length) {
                if (first[indexFirst] != second[indexSecond]) {
                    result[i] = first[indexFirst] < second[indexSecond] ? first[indexFirst++] : second[indexSecond++];
                } else {
                    result[i] = first[indexFirst++];
                    indexSecond++;
                    repeat--;
                }
            } else {
                if (indexFirst != first.length || indexSecond != second.length) {
                    result[i] = indexFirst >= first.length ? second[indexSecond] : first[indexFirst];
                }
            }
        }
        return Arrays.copyOf(result, repeat);
    }
}
