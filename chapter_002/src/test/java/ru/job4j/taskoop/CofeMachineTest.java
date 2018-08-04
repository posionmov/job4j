package ru.job4j.taskoop;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Класс для проверки работоспособности автомата для выдачи кофе и сдачи
 * @author Galanov Sergey
 * @since 04.08/2018
 * @version 1.0
 */
public class CofeMachineTest {

    /**
     * Содержит приватные ссылки
     * printstream - ссылка на стандарный вывод в консоль
     * out - ссылка на массив байтов из консоли
     * menu - стандартное меню
     */
    private PrintStream printstream;
    private ByteArrayOutputStream out;
    private String menu = new StringBuilder().append("Доступные кофе в данный момент: ").append(System.lineSeparator())
                                            .append("1 - Капучино, цена - 20").append(System.lineSeparator())
                                            .append("2 - Лотте, цена - 18").append(System.lineSeparator())
                                            .append("3 - Экспрессо, цена - 17").append(System.lineSeparator())
                                            .append("4 - Американо, цена - 23").append(System.lineSeparator())
                                            .append("5 - Выйти, цена - 0").append(System.lineSeparator()).toString();

    /**
     * Содержит функцию, которая вызывается до вызова теста
     * создает ссылку на стандартный вывод в консоль
     * делает новый вывод (вмето консоли записывает в переменную)
     */
    @Before
    public void beforeTestBegin() {
        this.printstream = System.out;
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Содержит функцию, которая вызывается после срабатывания теста
     * Возращает вывод обратно в консоль
     */
    @After
    public void afterTestBeginn() {
        System.setOut(this.printstream);
    }

    /**
     * Тест для проверки корректности вывода при выборе Капучино
     * Так же проверяет корректность отдачи монет
     */
    @Test
    public void whenCapuchinoThanResult() {
        ValidateInput input = new ValidateInput(new InputForTests(new String[] {"1", "22", "5"}));
        new CofeMachine(input).init();
        String expect = new StringBuilder().append(this.menu)
                                            .append("Цена данного напитка - 20").append(System.lineSeparator())
                                            .append("Ваша сдача: 2").append(System.lineSeparator())
                                            .append("Вам выдадутся следующие монеты:").append(System.lineSeparator())
                                            .append("2").append(System.lineSeparator())
                                            .append(this.menu)
                                            .append("Завершаю работу").append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    /**
     * Тест корректности вывода при выборе Лотте
     * Так же проверяет корректность отдачи монет
     */
    @Test
    public void whenLotteThenResult() {
        ValidateInput input = new ValidateInput(new InputForTests(new String[] {"2", "96", "5"}));
        new CofeMachine(input).init();
        String expect = new StringBuilder().append(this.menu)
                                            .append("Цена данного напитка - 18").append(System.lineSeparator())
                                            .append("Ваша сдача: 78").append(System.lineSeparator())
                                            .append("Вам выдадутся следующие монеты:").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("10").append(System.lineSeparator())
                                            .append("5").append(System.lineSeparator())
                                            .append("2").append(System.lineSeparator())
                                            .append("1").append(System.lineSeparator())
                                            .append(this.menu)
                                            .append("Завершаю работу").append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }

    /**
     * Тест, при котором дается недостаточно денег в автомат
     */
    @Test
    public void whenNotEnoughMoneyThenCorrectResult() {
        ValidateInput input = new ValidateInput(new InputForTests(new String[] {"1", "15", "5"}));
        new CofeMachine(input).init();
        String expect = new StringBuilder().append(this.menu)
                .append("Цена данного напитка - 20").append(System.lineSeparator())
                .append("Вам не хватает: 5").append(System.lineSeparator())
                .append(this.menu)
                .append("Завершаю работу").append(System.lineSeparator()).toString();
        assertThat(new String(out.toByteArray()), is(expect));
    }


}
