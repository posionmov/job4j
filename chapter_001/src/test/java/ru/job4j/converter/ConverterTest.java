package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Converter test
 * @author Sergey Galanov
 * @since 20.07.2018
 * @version 1.0
 */
public class ConverterTest {

    /**
     * test for func "rubToDollar"
     */
    @Test
    public void when60RubleToDollarThen1() {
        Converter conv = new Converter();
        double result = conv.rubToDollar(60);
        double expected = 1;
        assertThat(result, is(expected));
    }

    /**
     * test for func ""
     */
    @Test
    public void when70RubleToEurothen1() {
        Converter conv = new Converter();
        double result = conv.rubToEuro(70);
        double expected = 1;
        assertThat(result, is(expected));
    }
}
