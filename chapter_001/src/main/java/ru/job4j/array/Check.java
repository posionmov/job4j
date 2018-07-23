package ru.job4j.array;

/**
 * Class for checking that all bjects in array is boolean and of the same type
 * @author Galanov Sergey
 * @since 23.07.2018
 * @version 1.1
 */
public class Check {

    public boolean mono(boolean[] array) {
        boolean result = true;
        for (int i = 1; i != array.length; i++) {
            if (array[i] != array[i - 1]) {
                result = false;
                break;
            }
        }
        return result;
    }
}
