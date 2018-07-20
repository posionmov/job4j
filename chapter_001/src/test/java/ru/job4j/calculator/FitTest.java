package ru.job4j.calculator;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Test for calculating ideal weight for man and woman
 */
public class FitTest {

    /**
     * Test for func "manWeight"
     */
    @Test
    public void whenManHeight180ThenWeightCloseTo92() {
        Fit fit = new Fit();
        double result = fit.manWeight(180);
        assertThat(result, closeTo(92.0, 0.1));
    }

    /**
     * Test for func "womanWeight"
     */
    @Test
    public void whenWomanHeight170ThenWeightCloseTo69() {
        Fit fit = new Fit();
        double result = fit.womanWeight(170);
        assertThat(result, closeTo(69.0, 0.1));
    }
}
