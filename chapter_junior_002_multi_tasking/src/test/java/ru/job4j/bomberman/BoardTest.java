package ru.job4j.bomberman;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для проверки игры в бомбермена
 * @author Galanov Sergey
 * @since 30.08.2018
 * @version 1.0
 */
public class BoardTest {

    /**
     * Тест, проверящий что если юзер попытался встать на залоченную ячейку, то он выбирал другую
     * Так же проверяет что пользователь должен корретно дойти только до определенной точки определенным путем:
     *   0 1 2 3 4
     * 0|0 0 - - -
     * 1|- 0 0 - -
     * 2|- - 0 - -
     * 3|- 0 0 - -
     * 4|0 0 - - -
     * Нули - не залоченные ячейки
     * Тире - залоченные ячейки
     * Игрок должен пройти по определенному маршруту и выйти в позицию [4][4]
     *
     * @throws InterruptedException
     */
    @Test
    public void whenBoardHaveLockElementsThenCantStandOnThisCell() throws InterruptedException {
        Board board = new Board(5);
        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board.length; j++) {
                board.board[i][j].lock();
            }
        }
        board.board[0][0].unlock();
        board.board[1][0].unlock();
        board.board[1][1].unlock();
        board.board[2][1].unlock();
        board.board[2][2].unlock();
        board.board[2][3].unlock();
        board.board[1][3].unlock();
        board.board[1][4].unlock();
        board.board[0][4].unlock();
        User user = new User(0, 0, board.board);

        Thread first = new Thread(() -> {
            while (!(user.position.curX == 0 && user.position.curY == 4)) {
                final Cell cell = user.checkDirection(user.position);
                user.move(user.position, cell);
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        first.start();
        first.join();
    }

    /**
     * Тест проверки игрока и чудовища
     * Игрок идет по заранее заданному направлению
     * На полу есть недоступные участки (куда наступать нельзя)
     * Так же на поле есть чудовища. Чудовище не может встать на клетку, где есть другое чудовище.
     * Если игрок натыкается на чудовище, он умирает
     * Количество чудовищ и закрытых участков растет в зависимости от размера поля
     */
    @Test
    public void whenBoardHaveFiveOnFiveCellsThenTwoMonstersAndNineBlockingCells() {

    }
}