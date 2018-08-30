package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Класс, реализующий пулы потоков
 * @author Galanov Sergey
 * @since 30.08.2018
 * @version 1.0
 */
public class EmailNotification {

    /**
     * Приватное поле класса
     * Содержит ссылку на обьект класса ExecutorService, который используется для создания пула потоков
     */
    private ExecutorService pool;

    /**
     * Конструктор класса
     * Создает количество потоков, равное количеству свободных (доступных) процессоров
     */
    public EmailNotification() {
        this.pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    /**
     * Метод создания почты для пользователя
     * В методе создается массив строк, у которого:
     *  0 элемент - это тема письма
     *  1 элемент - само письмо (его тело)
     * @param user - пользователь
     */
    public void emailTo(User user) {
        final String[] email = new String[2];
        this.pool.submit(new Runnable() {
            @Override
            public void run() {
                email[0] = new StringBuilder()
                        .append("Notification ")
                        .append(user.getUserName())
                        .append(" to e-mail ")
                        .append(user.getEmail())
                        .toString();
                email[1] = new StringBuilder()
                        .append("Add a new event to ")
                        .append(user.getUserName())
                        .toString();
                send(email[0], email[1], user.getEmail());
            }
        });
    }

    /**
     * Метод, закрывающий пул потоков
     * Переводт в режим сна до момента, когда пул будет уничтожен
     */
    public void close() {
        this.pool.shutdown();
        while (!this.pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Метод отправки почты пользователю
     * @param suject - тема письма
     * @param body - само письмо
     * @param email - e-mail, на который требуется отправить письмо
     */
    public void send(String suject, String body, String email) { }

    /**
     * Приватный класс, описывающий пользователя
     */
    private class User {

        /**
         * Приватные поля класса
         * - имя пользователя
         * - почта пользователя
         */
        private String userName;
        private String email;

        /**
         * Конструктор класса
         * @param userName имя нового пользователя
         * @param email почта нового пользвателя
         */
        public User(String userName, String email) {
            this.userName = userName;
            this.email = email;
        }

        /**
         * Геттер имени пользователя
         * @return
         */
        public String getUserName() {
            return userName;
        }

        /**
         * Геттер почты пользователя
         * @return
         */
        public String getEmail() {
            return email;
        }
    }

}
