package ru.job4j.notify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Класс для реализации шаблона Producer Consumer
 * @author Galanov Sergey
 * @since 24.08.2018
 * @version 1.0
 * @param <T> - любой класс
 */
@ThreadSafe
public class SimplyBlockingQuene<T> {

    /**
     * Поля данного класса
     * Содержит в себе только внутреннюю коллекцию очереди
     */
    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();

    /**
     * Метод добавления элемента во внутреннюю коллекцию
     * При добавлении оповещает все потоки, которые ждали данный обьект проснуться
     *                                                  и проверить свое состояние
     * @param value обьект класса Т, который следует поместить во внутреннюю коллекцию
     */
    public void offer(T value) {
        synchronized (this.queue) {
            this.queue.add(value);
            System.out.println("Поток оповестился");
            this.queue.notify();
        }
    }

    /**
     * Возращает обьект из внутренней коллекции
     * Если в коллеции нет обьектов, то поток должен перейти в состояние ожидания
     * @return первый обьект из внутренней колекции
     */
    public T poll() throws InterruptedException {
        synchronized (this.queue) {
            while (this.queue.size() == 0) {
                System.out.println("Поток стал ждать");
                this.queue.wait();
            }
        }
        System.out.println("Поток проснулся и закончил работу");
        return this.queue.poll();
    }

}
