package ru.job4j.xml_example;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования работы парсера XML файлов
 * @author Galanov Sergey
 * @since 10.09.2018
 * @version 1.0
 */
public class ResultSummTest {

    /**
     * Тест на проверку корректности подсчета
     */
    @Test
    public void whenCreate10ItemsThenResultIs55() {
        final String dir = System.getProperty("user.dir");
        ResultSumm res = null;
        try {
            res = new ResultSumm(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(res.getResult(), is(55d));
    }

}