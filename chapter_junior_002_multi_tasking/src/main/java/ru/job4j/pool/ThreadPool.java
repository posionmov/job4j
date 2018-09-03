package ru.job4j.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Класс, реализующий работу очереди заданий и коллекции потоков
 * @author Galanov Sergey
 * @since 03.09.2018
 * @version 1.1
 */
public class ThreadPool {

    /**
     * Приватные поля класса:
     *  - размер коллекции потоков (в зависимости от количества доступных процессоров)
     *  - коллекция потоков
     *  - очередь из заданий
     *  - количество работающих потоков
     */
    private final int size = Runtime.getRuntime().availableProcessors();
    private final List<Test> threads = new LinkedList<>();
    private final Queue<Runnable> tasks = new LinkedBlockingQueue<>();
    private volatile int curSize = 0;

    /**
     * Приватный класс, наследник класса Thread
     * Выполнет функцию потока
     */
    private class Test extends Thread {

        private boolean isWork = true;

        /**
         * Метод, который выполняется в рамках одного потока
         * Если очередь пустая, то поток впадает в ожидание
         * После каждой итерации поток впадает в ожидание
         */
        @Override
        public void run() {
            synchronized (tasks) {
                while (!isInterrupted()) {
                    try {
                        while (tasks.isEmpty()) {
                            tasks.wait();
                        }
                        tasks.peek().run();
                        try {
                            tasks.wait();
                        } catch (InterruptedException e) {

                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.printf("Поток %s остановлен%s", Thread.currentThread().getName(), System.lineSeparator());
            }
        }
    }

    /**
     * Конструктор класса
     * Запускает некоторое кол-во потоков (в зависимости от доступных процессоров)
     *  и добавляет их в массив потоков. Кроме того при каждой итерации увеличивает величину
     *  внутренней переменной curSize на 1
     */
    public ThreadPool() {
        for (int i = 0; i < size; i++) {
            Test test = new Test();
            test.start();
            this.threads.add(test);
            this.curSize += 1;
        }
    }

    /**
     * Метод, добавляющий в очередь задание
     * @param job - обьект класса, реализующий интерфейс runnable
     */
    public void work(Runnable job) {
        synchronized (this.tasks) {
            tasks.add(job);
            tasks.notifyAll();
        }
    }

    /**
     * Метод выключения
     * Вызывает у каждого потока метод stopThread(), который останавливает текущий поток
     * Так же при каждой итерации цикла уменьшает значение внутренней переменной curSize на 1
     */
    public void shutdown() {
        System.out.println("Выключение");
        synchronized (this.threads) {
            for (Test thread : this.threads) {
                thread.interrupt();
                this.curSize -= 1;
            }
        }
    }

    /**
     * Вспомогательный метод, позращающий количество потоков
     * @return количество потоков
     */
    public int getSizeOfCurrentThreads() {
        return this.curSize;
    }
}
