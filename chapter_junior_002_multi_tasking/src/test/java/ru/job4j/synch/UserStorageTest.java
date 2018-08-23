package ru.job4j.synch;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования UserStorage на предмет многопоточности
 * @author Galanov Sergey
 * @since 23.08.2018
 * @version 1.0
 */
public class UserStorageTest {

    /**
     * Приватный класс, выступающий в роли потока
     */
    private class StorageThread extends Thread {

        /**
         * Внутреннее поле
         * содержит ссылку на обьект класса UserStorage, который служит контейнером для User-ов
         */
        private UserStorage storage;

        /**
         * Конструктор класса
         * @param storage ссылка на обьект класса UserStorage
         */
        private StorageThread(UserStorage storage) {
            this.storage = storage;
        }

        /**
         * Метод потока
         * Осуществляет перевод денег с одного счета на другой
         * Поиск счетов осуществляет по id
         */
        @Override
        public void run() {
            System.out.println("Уменьшил с: " + storage.getValue(1) + " до: " + (storage.getValue(1) - 10));
            this.storage.transfer(1, 2, 10);

        }

    }

    /**
     * Тест проверки корректности осуществления переводов между Юзерами в разрезе потоков
     * @throws InterruptedException - если главный поток завершится раньше, чем выйдет из сна
     */
    @Test
    public void whenTransferInThreadThenSynchAllThreads() throws InterruptedException {
        UserStorage storage = new UserStorage();
        storage.add(new User(1, 100));
        storage.add(new User(2, 100));
        for (int i = 0; i < 5; i++) {
            StorageThread thread = new StorageThread(storage);
            thread.start();
        }
        Thread.sleep(1000);
        assertThat(storage.getValue(1), is(50));
        assertThat(storage.update(new User(2, 300)), is(true));
        assertThat(storage.delete(new User(2, 300)), is(true));
        assertThat(storage.getSize(), is(1));
    }

}