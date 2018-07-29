package ru.job4j.exam;
import java.util.Arrays;

/**
 * Class for merge two sorted arrays
 * @author Galanov Sergey
 * @since 29.07.2018
 * @version 1.0
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
            if ((indexFirst <= first.length - 1) && (indexSecond <= second.length - 1)) {
                if (first[indexFirst] > second[indexSecond]) {
                    result[i] = second[indexSecond++];
                } else if (first[indexFirst] < second[indexSecond]) {
                    result[i] = first[indexFirst++];
                } else if (first[indexFirst] == second[indexSecond]) {
                    result[i] = first[indexFirst];
                    indexFirst++;
                    indexSecond++;
                    repeat--;
                }
            } else if ((indexFirst <= first.length - 1) || (indexSecond <= second.length - 1)) {
                if (indexFirst >= first.length) {
                    result[i] = second[indexSecond];
                } else if (indexSecond >= second.length) {
                    result[i] = first[indexFirst];
                }
            }
        }
        return Arrays.copyOf(result, repeat);
    }
}
