package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, описывающий игральную доску, по которой передвигаются игроки
 * @author Galanov Sergey
 * @since 03.09.2018
 * @version 1.1
 */
public class Board {
    /**
     * Приватное поле
     * Содержит в себе двумертный массив обьектов ReentrantLock
     */
    public final ReentrantLock[][] board;

    /**
     * Конструктор класса
     * Создает поле в зависимости от указанных размеров
     * Затем создает непроходимые области на карте. Количество непроходимых полей: размер полня / 2
     * Затем создает некоторое количество монстров на карте. Количество монстров на карте: размер карты / 3 * 2
     * todo сделать генерацию монстров и закрытых участков поля
     * todo так же сделть скрипт рандомных передвижений монстров и проверки других монстров на клетках
     * @param size размер поля
     */
    public Board(int size) {
        this.board = new ReentrantLock[size][size];
        for (int i = 0; i < this.board.length; i++) {
            for (int j = 0; j < this.board.length; j++) {
                this.board[i][j] = new ReentrantLock();
            }
        }
    }

    /**
     * Метод, обеспечивающий передвижение по доске
     * Проверяет возможность встать на новую клетку (т.е. проверяет замок на клетке). Если замка нет, то блокирует эту ячейку.
     * После блокировки ячейки пытается разблокировать ячейку, на которой был. Это произойдет только в том случае, если
     *  этот же поток блокировал данную ячейку
     * @param source - исходная ячейка
     * @param dist - следующая ячейка
     * @return - true если удалось встать на новую ячейку
     */
    public boolean move(Cell source, Cell dist) {
        boolean result = false;
        if (this.board[dist.curX][dist.curY].tryLock()) {
            System.out.printf("Игрок %s пошел на ячейку X:%s Y:%s%s", Thread.currentThread().getName(), dist.curX, dist.curY, System.lineSeparator());
            this.board[dist.curX][dist.curY].tryLock();
            System.out.printf("%s - Ячейка X:%s Y:%s освобождена%s", Thread.currentThread().getName(), source.curX, source.curY, System.lineSeparator());
            if (this.board[source.curX][source.curY].isHeldByCurrentThread()) {
                this.board[source.curX][source.curY].unlock();
            }
            result = true;
        } else {
            System.out.printf("%s - Ячейка X:%s Y%s ЗАНЯТА%s", Thread.currentThread().getName(), dist.curX, dist.curY, System.lineSeparator());
        }
        return result;
    }

    /**
     * Вспомогательный метод калькуляции смещения игрока относительно оси Х при попытке встать на залоченную ячейку.
     * вычисляет смещение, учитывая выход за пределы
     * @param x - изначальная позиция по Х
     * @return
     */
    public int calculateChanges(int x) {
        return ((x + 1) > this.board.length - 1) ? (x + 1) : ((x - 1) <= 0) ? (x + 1) : (x - 1);
    }
}

/**
 * Класс, описывающий поведение игрока
 */
class User {

    /**
     * Приватные поля класса
     * Содержат:
     *  - ссылку на массив ячеек доски
     *  - текущую позицию на доске
     */
    private int curX;
    private int curY;
    private final ReentrantLock[][] board;
    public Cell position;

    /**
     * Конструктор класса
     * Так же при инициализации блокирует начальную ячейку
     * @param curX - первоначальная позиция по Х
     * @param curY - первоначальная позиция по У
     * @param board - ссылка на текущую доску
     */
    public User(int curX, int curY, ReentrantLock[][] board) {
        this.board = board;
        this.curX = curX;
        this.curY = curY;
        this.position = new Cell(curX, curY);
        this.board[curX][curY].lock();
    }

    /**
     * Метод, возращающий значение числа Х, перед этим увеличив его на 1
     * @return проинкрементированное значение Х
     */
    public int getAndIncrementCurX() {
        return ++this.curX;
    }

    /**
     * Метод, производящий инкремент числа Х и возраащющий его
     * @return инкремент числа Х
     */
    public int getAndIncrementCurY() {
        return ++this.curY;
    }

    /**
     * Метод, производящий декремент числа Х и возращающий результат
     * @return декремент числа Х
     */
    public int getAndDecrementCurX() {
        return --this.curX;
    }

    /**
     * Метод, производящий декремент числа У и возращающий результат
     * @return декремент числа У
     */
    public int getAndDecrementCurY() {
        return --this.curY;
    }

    /**
     * Сеттер значения числа Х
     * @param curX
     */
    public void setCurX(int curX) {
        this.curX = curX;
    }

    /**
     * Геттер значения числа Х
     * @return
     */
    public int getCurX() {
        return curX;
    }

    /**
     * Геттер значения числа У
     * @return
     */
    public int getCurY() {
        return curY;
    }

    /**
     * Сеттер значения числа У
     * @param curY
     */
    public void setCurY(int curY) {
        this.curY = curY;

    }
}

/**
 * Класс, необходимый для хранения пложения по Х и У игрока
 */
class Cell {

    /**
     * Внутренние поля класса
     */
    int curX;
    int curY;

    /**
     * Конструктор класса
     * @param curX
     * @param curY
     */
    public Cell(int curX, int curY) {
        this.curX = curX;
        this.curY = curY;
    }
}
