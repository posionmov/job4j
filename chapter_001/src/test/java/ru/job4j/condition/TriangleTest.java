package ru.job4j.condition;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
 * Class for testing Triangle.java
 */
public class TriangleTest {

    /**
     * Func for testing func "area"
     */
    @Test
    public void thenSetPoints00And02And20Then01() {
        Point a = new Point(0, 0);
        Point b = new Point(0, 2);
        Point c = new Point(2, 0);

        Triangle triangle = new Triangle(a, b, c);
        assertThat(triangle.area(), closeTo(2D, 0.1));
    }
}
