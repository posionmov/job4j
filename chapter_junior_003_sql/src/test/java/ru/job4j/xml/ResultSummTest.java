package ru.job4j.xml;


import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования работы парсера XML файлов
 * @author Galanov Sergey
 * @since 19.09.2018
 * @version 1.1
 */
public class ResultSummTest {

    /**
     * Тест на проверку корректности подсчета
     */
    @Test
    public void whenCreate10ItemsThenResultIs55() {
        ResultSumm res = null;
        try {
            res = new ResultSumm();
            res.start(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertThat(res.getResult(), is(55d));
    }

}