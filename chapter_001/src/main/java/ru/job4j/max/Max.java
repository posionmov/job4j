package ru.job4j.max;

/**
 * Class for calculating maximum from two numbers
 * @author Sergey Galanov
 * @since 21.07.2018
 * @version 1.0
 */
public class Max {

    /**
     * Func for returning max number from tho numbers
     * @param first - first number
     * @param second - second number
     * @return max from these two numbers
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }

    /**
     * Func for finding max from three numbers
     * @param first - first number
     * @param second - second number
     * @param third - third number
     * @return max from three numbers
     */
    public int max(int first, int second, int third) {
        return max(max(first, second), third);
    }
}
