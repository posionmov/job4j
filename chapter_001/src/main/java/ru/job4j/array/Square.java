package ru.job4j.array;

/**
 * Class for lesson about arrays
 * @author Galanov Sergey
 * @since 22.07.2018
 * @version 1.0
 */
public class Square {


    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (int) Math.pow(i + 1, 2);
        }
        return rst;
    }
}
