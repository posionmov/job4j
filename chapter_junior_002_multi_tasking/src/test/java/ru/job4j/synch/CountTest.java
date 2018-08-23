package ru.job4j.synch;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class CountTest {

    private class ThreadCount extends Thread {

        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecute2ThreadsThen2() throws InterruptedException {

        final Count count = new Count(); //Создаем счетчик.

        Thread first = new ThreadCount(count); //Создаем нити.
        Thread second = new ThreadCount(count);

        first.start(); //Запускаем нити.
        second.start();

        first.join(); //Заставляем главную нить дождаться выполнения наших нитей.
        second.join();

        assertThat(count.get(), is(2)); //Проверяем результат.
    }

}