package ru.job4j.tracker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ValidateInputTest {

    private final PrintStream standartOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private String menu = new StringBuilder().append("0 - Создание заявки").append(System.lineSeparator())
            .append("1 - Показать все заявки").append(System.lineSeparator())
            .append("2 - Редактирование заявки").append(System.lineSeparator())
            .append("3 - Удаление заявки").append(System.lineSeparator())
            .append("4 - Поиск заявки по id").append(System.lineSeparator())
            .append("5 - Поиск заявки по имени").append(System.lineSeparator())
            .append("6 - Выйти из программы").append(System.lineSeparator())
            .append("----------------------------------------------------------").append(System.lineSeparator()).toString();


    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }


    @After
    public void choseStandartOut() {
        System.setOut(this.standartOut);
    }

    @Test
    public void whenInvalidInputThenException() {
        Tracker tracker = new Tracker();
        ValidateInput input = new ValidateInput(new StubInput(new String[] {"qwe", "6"}));
        new StartUI(input, tracker).init();
        assertThat(new String(this.out.toByteArray()), is(new StringBuilder()
                .append(this.menu)
                .append("Пожалуйста введите число").append(System.lineSeparator())
                .append("Выключение").append(System.lineSeparator()).toString()));
    }
}
