package ru.job4j.exam;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayMerge {

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
