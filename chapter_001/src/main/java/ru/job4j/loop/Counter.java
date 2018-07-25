package ru.job4j.loop;

/**
 * Class for lesson about looping
 * @author Galanov  Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class Counter {

    /**
     * Func for finding summ of all odd numbers from Start to Finish
     * @param start - first number
     * @param finish - last number
     * @return summ of all add numbers
     */
    public int add(int start, int finish) {
        int result = 0;
        for (int i = start; i <= finish; i++) {
            if (i % 2 == 0) {
                result += i;
            }
        }
        return  result;
    }
}
