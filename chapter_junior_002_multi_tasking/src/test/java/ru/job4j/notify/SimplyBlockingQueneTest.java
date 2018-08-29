package ru.job4j.notify;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестов синхронизации и изменения состояния потоков
 * @author Galanov Sergey
 * @since 24.08.2018
 * @version 1.0
 */
public class SimplyBlockingQueneTest {

    /**
     * Локальная переменная
     * Содержит в себе полученное в будущем значение  из очереди
     */
    private int value;

    /**
     * Тест на изменение состояния потока если первым запустится потребитель
     * @throws InterruptedException если поток будет прерван при выполнении операции
     */
    @Test
    public void whenQueneIsEmptyThenFirstWorkIsOffer() throws InterruptedException {
        SimplyBlockingQuene<Integer> queue = new SimplyBlockingQuene<>();
        Thread consumer = new Thread(() -> {
            try {
                value = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread producer = new Thread(() -> queue.offer(1));
        consumer.start();
        producer.start();
        consumer.join();
        producer.join();
        assertThat(value, is(1));
    }

    /**
     * Тест на изменение состояния потока если первым запустится производитель
     */
    @Test
    public void whenProducerStartFirstThenConsumerDontWait() throws InterruptedException {
        SimplyBlockingQuene<Integer> queue = new SimplyBlockingQuene<>();
        Thread consumer = new Thread(() -> {
            try {
                value = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread producer = new Thread(() -> queue.offer(1));
        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
        assertThat(value, is(1));
    }

}