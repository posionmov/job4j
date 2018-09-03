package ru.job4j.bomberman;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для проверки игры в бомбермена
 * @author Galanov Sergey
 * @since 03.09.2018
 * @version 1.1
 */
public class BoardTest {

    /**
     * Тест, проверяющий как работает передвижение персонажа
     */
    @Test
    public void whenThen() throws InterruptedException {
        Board board = new Board(5);
        User user1 = new User(0, 0, board.board);
        Thread userOne = new Thread(() -> {
            for (int i = 0; i < board.board.length - 1; i++) {
                if (!board.move(new Cell(user1.getCurX(), user1.getCurY()), new Cell(user1.getAndIncrementCurX(), user1.getAndIncrementCurY()))) {
                    user1.setCurX(board.calculateChanges(user1.getCurX()));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        User user2 = new User(board.board.length - 1, board.board.length - 1, board.board);
        Thread userTwo = new Thread(() -> {
            for (int i = 0; i < board.board.length - 1; i++) {
                if (!board.move(new Cell(user2.getCurX(), user2.getCurY()), new Cell(user2.getAndDecrementCurX(), user2.getAndDecrementCurY()))) {
                    user2.setCurX(board.calculateChanges(user2.getCurX()));
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        userOne.start();
        userTwo.start();
        userOne.join();
        userTwo.join();
        System.out.println(user1.getCurX() + " " + user1.getCurY());
        System.out.println(user2.getCurX() + " " + user2.getCurY());
        assertThat(user1.getCurX(), is(3));
        assertThat(user1.getCurY(), is(4));
        assertThat(user2.getCurX(), is(1));
        assertThat(user2.getCurY(), is(0));
    }
}