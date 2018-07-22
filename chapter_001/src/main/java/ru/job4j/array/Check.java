package ru.job4j.array;

/**
 * Class for checking that all bjects in array is boolean and of the same type
 */
public class Check {

    public boolean mono(boolean[] array) {
        boolean result = false;
        for (int i = 1; i != array.length; i++) {
            if (array[i] == array[i - 1]) {
                result = true;
            } else {
                result = false;
            }
        }
        return result;
    }
}
