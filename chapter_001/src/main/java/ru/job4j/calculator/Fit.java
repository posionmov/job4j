package ru.job4j.calculator;

/**
 * Class for claculate ideal weight for man and woman
 * @author Sergey Galanov
 * @since 20.07.2018
 * @version 1.0
 */
public class Fit {

    /**
     * Func for calculate ideal weight for man
     * @param height - current heihgt of man
     * @return ideal weight for man with this height
     */
    public double manWeight(double height) {
        return (height - 100) * 1.15;
    }

    /**
     * Func for calculate ideal weight for woman
     * @param height - current weight of woman
     * @return ideal weight for woman with this height
     */
    public double womanWeight(double height) {
        return (height - 110) * 1.15;
    }
}
