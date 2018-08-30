package ru.job4j.bomberman;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Класс, описывающий игральную доску, по которой передвигаются игроки
 * @author Galanov Sergey
 * @since 30.08.2018
 * @version 1.0
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
     * todo сделать генерацию понстров и закрытых участков поля
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
        this.position = new Cell(curX, curY);
    }

    public boolean move(Cell source, Cell dist) {
        boolean result = false;
        if (!board[dist.curX][dist.curY].tryLock() && source.curX != dist.curX && source.curY != dist.curY) {
            board[position.curX][position.curY].unlock();
            board[dist.curX][dist.curY].lock();
            this.position = dist;
        }
        return result;
    }

    /**
     * Вспомогательный приватный метод, проверяющий ячейку
     * Если передаваемая ячейка не мб залочена текущим обьектом, то передаваемая в функцию ячейка не изменится
     * @param x - значение Х проверяемой клетки
     * @param y - значение У проверяемой клетки
     * @param cell - клетка
     * @return новую клетку на основе старой. Так как если не выполняются условия, то ссылка будет не изменна для ячейки,
     *         то их в итоге можно сравнивать методом equals
     */
    private Cell checkCell(int x, int y, Cell cell) {
        if (this.board[x][y].tryLock()) {
            cell.curX = x;
            cell.curY = y;
            board[cell.curX][cell.curY].lock();
            System.out.printf("Позиция X:%s  Y:%s ПОДОШЛА!%s", cell.curX, cell.curY, System.lineSeparator());
        }
        return cell;
    }

    /**
     * Приватный метод проверки возможности направления движения
     * Изменяет текущее положение игрока
     * Сперва определяет возможность пойти в 4 стороны в зависимости от положения игрока и краев доски
     * Затем запускается цикл, в котором производится перестановка игрока
     */
    public Cell checkDirection(Cell userPosition) {
        Cell cell = userPosition;
        boolean ex = false;
        // Блок проверки возможности передвижения по сторонам
        boolean canUp = this.position.curY - 1 >= 0;
        boolean canDown = this.position.curY + 1 < this.board.length;
        boolean canLeft = this.position.curX - 1 >= 0;
        boolean canRight = this.position.curX + 1 < this.board.length;
        while (!ex) {
            int rand = this.getRand();
            if (rand == 0 && canUp) {
                Cell newCell = this.checkCell(this.position.curX, this.position.curY - 1, cell);
                if (newCell.equals(cell)) {
                    ex = true;
                }
            } else if (rand == 1 && canDown) {
                Cell newCell = this.checkCell(this.position.curX, this.position.curY + 1, cell);
                if (newCell.equals(cell)) {
                    ex = true;
                }
            } else if (rand == 2 && canLeft) {
                Cell newCell = this.checkCell(this.position.curX - 1, this.position.curY, cell);
                if (newCell.equals(cell)) {
                    ex = true;
                }
            } else if (rand == 3 && canRight) {
                Cell newCell = this.checkCell(this.position.curX + 1, this.position.curY, cell);
                if (newCell.equals(cell)) {
                    ex = true;
                }
            }
            try {
                String msg = rand == 0 ? "Вверх" : rand == 1 ? "Вниз" : rand == 2 ? "На лево" : rand == 3 ? "На право" : "default";
                System.out.printf("%s не получилось, жду пол секунды %s", msg, System.lineSeparator());
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return cell;
    }

    /**
     * Метод, выдащий случайное направление.
     * Возможные варианты:
     *  0 - вверх
     *  1 - вниз
     *  2 - влево
     *  3 - вправо
     * @return
     */
    public int getRand() {
        return new Random().nextInt(4);
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
