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
     * Тест на изменение состояния потока
     * @throws InterruptedException если поток будет прерван при выполнении операции
     */
    @Test
    public void whenQueneIsEmptyThenFirstWorkIsOffer() throws InterruptedException {
        SimplyBlockingQuene<Integer> queue = new SimplyBlockingQuene<>();
        Thread poll = new Thread(() -> {
            try {
                value = queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread offer = new Thread(() -> queue.offer(1));
        poll.start();
        offer.start();
        poll.join();
        offer.join();
        assertThat(this.value, is(1));
    }
}