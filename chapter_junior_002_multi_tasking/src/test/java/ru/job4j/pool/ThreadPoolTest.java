package ru.job4j.pool;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования пулов потоков
 * @author Galanov Sergey
 * @since 03.09.2018
 * @version 1.1
 */
public class ThreadPoolTest {

    /**
     * Приватные поля тестового класса
     * Содержат:
     *  - ссылку на стандартный вывод в консоль
     *  - битовый массив out потока
     */
    private final PrintStream standartOut = System.out;
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Метод, вызываемый до выполнения теста
     * Перенаправляет вывод из консоли в нашу переменную
     */
    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    /**
     * Метод, вызываемый после выполнения теста
     * Перенаправляет выход с нашей переменной на стандартный выход
     */
    @After
    public void choseStandartOut() {
        System.setOut(this.standartOut);
    }

    /**
     * Тест, добаляющий в очередь одно задание и проверяющий корректность вывода на экран
     * @throws InterruptedException
     */
    @Test
    public void when() throws InterruptedException {
        ThreadPool pool = new ThreadPool();
        pool.work(() -> System.out.println("run"));
        Thread.sleep(1000);
        assertThat(new String(this.out.toByteArray()), is("run" + System.lineSeparator()
                + "run" + System.lineSeparator()
                + "run" + System.lineSeparator()
                + "run" + System.lineSeparator()));
        pool.shutdown();
    }

    /**
     * Тест на корректную остановку тредов
     */
    @Test
    public void whenCreateThreadsAndStopTheThenSizeInNull() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();
        Thread.sleep(1000);
        threadPool.work(() -> System.out.printf("-- Thread %s do something --%s", Thread.currentThread().getName(), System.lineSeparator()));
        threadPool.shutdown();
        assertThat(threadPool.getSizeOfCurrentThreads(), is(0));
    }
}