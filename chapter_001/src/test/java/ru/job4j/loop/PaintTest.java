package ru.job4j.loop;

import java.util.StringJoiner;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Test class for Paint.java
 * @author Galanov Sergey
 * @since 21.07.2018
 * @version 1.0
 */
public class PaintTest {

    /**
     * test for first func (right part of pyramid)
     */
    @Test
    public void when4ThenPyramidIs4Right() {
        Paint paint = new Paint();
        String rst = paint.rightTrl(4);
        System.out.println(rst);

        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("^   ")
                .add("^^  ")
                .add("^^^ ")
                .add("^^^^")
                .toString()));
    }

    /**
     * test for second func (left part of pyramid)
     */
    @Test
    public void when4ThenPyramidIs4Left() {
        Paint paint = new Paint();
        String rst = paint.leftTRL(4);
        System.out.println(rst);

        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("   ^")
                .add("  ^^")
                .add(" ^^^")
                .add("^^^^")
                .toString()));
    }

    /**
     * test for final func (all parts of pyramid)
     */
    @Test
    public void when4ThenPyramid4All() {
        Paint paint = new Paint();
        String rst = paint.pyramid(4);
        System.out.println(rst);
        assertThat(rst, is(new StringJoiner(System.lineSeparator(), "", System.lineSeparator())
                .add("   ^   ")
                .add("  ^^^  ")
                .add(" ^^^^^ ")
                .add("^^^^^^^")
                .toString()));
    }
}
