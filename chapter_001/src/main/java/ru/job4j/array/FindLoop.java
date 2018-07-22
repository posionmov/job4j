package ru.job4j.array;

/**
 * Class for finding some obj in array
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class FindLoop {

    public int indexOf(int[] data, int element) {
        int result = -1;
        for (int el = 0; el != data.length; el++) {
            if (data[el] == element) {
                result = el;
                break;
            }
        }
        return result;
    }
}
