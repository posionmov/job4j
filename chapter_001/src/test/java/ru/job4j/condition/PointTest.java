package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.*;

/**
 * Class for testing class Point.java
 */
public class PointTest {

    /**
     * Test for func "distanceTo"
     */
    @Test
    public void whenPointAIsX5Y3AndBIsX2Y6ThenDistanceBetweenIs4() {
        Point a = new Point(5, 3);
        Point b = new Point(2, 6);
        double result = a.distanceTo(b);
        double expect = 4;
        assertThat(result, closeTo(expect, 0.25));
    }
}
