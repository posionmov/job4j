package ru.job4j.loop;

/**
 * Class for task about Factorial
 * @author Galanov Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class Factorial {

    /**
     * Func for calculating factorial of number
     * @param n
     * @return factorial of number n
     */
    public int calc(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
