package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Calculator test
 * @author Sergey Galanov
 * @since 20.07.2018
 * @version 1.0
 */
public class CalculatorTest {

    /**
     * Test func "add"
     */
    @Test
    public void whenAddOnePlusOneThenTwo() {
        Calculator calc = new Calculator();
        calc.add(1D, 1D);
        double result = calc.getResult();
        double expected = 2D;
        assertThat(result, is(expected));
    }

    /**
     * Test for func "subtract"
     */
    @Test
    public void whenSevenMinusThreeThenFour() {
        Calculator calc = new Calculator();
        calc.subtract(7, 3);
        double result = calc.getResult();
        double expected = 4;
        assertThat(result, is(expected));
    }

    /**
     * Test for func "div"
     */
    @Test
    public void whenEightDivFourThenTwo() {
        Calculator calc = new Calculator();
        calc.div(8, 4);
        double result = calc.getResult();
        double expected = 2;
        assertThat(result, is(expected));
    }

    /**
     * Test for func "multiple"
     */
    @Test
    public void whenThreeMultiTwoThenSix() {
        Calculator calc = new Calculator();
        calc.multiple(3, 2);
        double result = calc.getResult();
        double expected = 6;
        assertThat(result, is(expected));
    }

}
