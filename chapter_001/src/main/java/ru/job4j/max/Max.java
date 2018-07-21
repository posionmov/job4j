package ru.job4j.max;

/**
 * Class for calculating maximum from two numbers
 * @author Sergey Galanov
 * @since 21.07.2018
 * @version 1.0
 */
public class Max {

    /**
     * Class for returning max number from tho numbers
     * @param first - first number
     * @param second - second number
     * @return max from these two numbers
     */
    public int max(int first, int second) {
        return first > second ? first : second;
    }
}
