package ru.job4j.exam;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CompareStringsTest {

    /**
     * Тест при условии, что строки равны
     */
    @Test
    public void whenStringsAreSameThenReturnTrue() {
        String one = "String";
        String two = "String";
        CompareStrings strings = new CompareStrings();
        boolean result = strings.compareString(one, two);
        assertThat(result, is(true));
    }

    /**
     * Тест если у строк равное количество букв, но разное их количество
     */
    @Test
    public void whenStringsHaveSameCharactersButDifferentQuantityThenReturnFalse() {
        String one = "String";
        String two = "SString";
        CompareStrings strings = new CompareStrings();
        boolean result = strings.compareString(one, two);
        assertThat(result, is(false));
    }

    /**
     * Тест если строки разные
     */
    @Test
    public void whenStringsAreNotSameThenReturnFalse() {
        String one = "String2";
        String two = "String1";
        CompareStrings strings = new CompareStrings();
        boolean result = strings.compareString(one, two);
        assertThat(result, is(false));
    }

    /**
     * Тест если у строк одинаковые буквы и количество, но разный порядок
     */
    @Test
    public void whenStringsAreSameButDiffSequenceThenReturnTrue() {
        String one = "String";
        String two = "nrStig";
        CompareStrings strings = new CompareStrings();
        boolean result = strings.compareString(one, two);
        assertThat(result, is(true));
    }

    /**
     * Тест, когда одинаковое колиество символов, но при этом они разные
     */
    @Test
    public void whenStringAreDifferentButValueOfCharactersAreSameThenReturnFalse() {
        String one = "12345";
        String two = "67891";
        CompareStrings strings = new CompareStrings();
        boolean result = strings.compareString(one, two);
        assertThat(result, is(false));
    }

    /**
     * Тест, когда символы одни и теже, но их повторы разные
     */
    @Test
    public void whenCharactersAreSameButInOtherConditionsThenReturnFalse() {
        String one = "11122";
        String two = "11222";
        CompareStrings strings = new CompareStrings();
        boolean result = strings.compareString(one, two);
        assertThat(result, is(false));
    }
}