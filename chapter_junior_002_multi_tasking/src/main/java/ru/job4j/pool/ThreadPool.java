package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Класс, реализующий работу очереди заданий и коллекции потоков
 * @author Galanov Sergey
 * @since 29.08.2018
 * @version 1.0
 */
public class ThreadPool {

    /**
     * Приватные поля класса:
     *  - размер коллекции потоков (в зависимости от количества доступных процессоров)
     *  - коллекция потоков
     *  - очередь из заданий
     */
    private int size = Runtime.getRuntime().availableProcessors();
    private final List<Thread> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();

    /**
     * Приватный класс, наследник класса Thread
     * Выполнет функцию потока
     */
    private class Test extends Thread {

        /**
         * Метод, который выполняется в рамках одного потока
         * Если очередь пустая, то поток впадает в ожидание
         */
        public void run() {
            synchronized (tasks) {
                try {
                    while (tasks.isEmpty()) {
                        tasks.wait();
                    }
                    tasks.peek().run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Конструктор класса
     * Запускает некоторое кол-во потоков (в зависимости от доступных процессоров)
     *  и добавляет их в массив потоков
     */
    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Test test = new Test();
            test.start();
            this.threads.add(test);
        }
    }

    /**
     * Метод, добавляющий в очередь задание
     * @param job - обьект класса, реализующий интерфейс runnable
     */
    public void work(Runnable job) {
        synchronized (tasks) {
            tasks.add(job);
            tasks.notifyAll();
        }
    }

    public void shutdown() {

    }
}
