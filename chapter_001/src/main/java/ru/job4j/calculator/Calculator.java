package ru.job4j.calculator;

/**
 * Calculate.
 * @author Sergey Galanov
 * @since 18.07.2018.
 * @version 1.0.
 */
public class Calculator {

    /**
     * Contain private variable (double)
     */
    private double result;

    /**
     * Func calculated the result of summ (first + second) and save result to the private variable (result)
     * @param first - 1-st number
     * @param second - 2-st number
     */
    public void add(double first, double second) {
        this.result = first + second;
    }

    /**
     * Func calculated the result of subtract (first - second) and save result to the private variable (result)
     * @param first - 1-st number
     * @param second - 2-st number
     */
    public void subtract(double first, double second) {
        this.result = first - second;
    }

    /**
     * Func calculated the result of dividing (first / second) and save result to the private variable (result)
     * @param first - 1-st number
     * @param second - 2-st number
     */
    public void div(double first, double second) {
        this.result = first / second;
    }

    /**
     * Func calculate the result of multiple (first 8 second) and save result to the private variable (result)
     * @param first - 1-st number
     * @param second - 2-st number
     */
    public void multiple(double first, double second) {
        this.result = first * second;
    }

    /**
     * Func for return value of private variable (result)
     * @return value - returning value of variable
     */
    public double getResult() {
        return this.result;
    }
}
