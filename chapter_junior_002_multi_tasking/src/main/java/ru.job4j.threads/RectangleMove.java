package ru.job4j.threads;

import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Random;

/**
 * Класс для обработки передвижения квадрата по полю
 * @author Galanov Sergey
 * @since 22.08.2018
 * @version 1.0
 */
public class RectangleMove implements Runnable {

    /**
     * Содержит внутренние поля класса
     * - rect: ссылка на обьект класса Rectangle
     * - limitX: максимально допустимая величина Х
     * - limitY: максимально допустимая величина E
     * - valueX: переменная для определения двиения по оси Х (если отрицательная - то вправо)
     * - valueY: переменная для определения двиения по оси У (если отрицательная - то вверх)
     * - randValueX: случайное значение для переменной Х
     * - randValueY: случайное значение для переменной У
     */
    private final Rectangle rect;
    private final int limitX;
    private final int limitY;
    private int valueX = 1;
    private int valueY = 1;
    private int randValueX; // Случайное число для Х
    private int randValueY; // Случайно число для У

    /**
     * Конструктор класса
     * @param rect ссылка на обьект класса Rectangle
     * @param maxX максимально допустимая величина Х
     * @param maxY максимально допустимая величина У
     */
    public RectangleMove(Rectangle rect, int maxX, int maxY) {
        this.rect = rect;
        this.limitX = maxX;
        this.limitY = maxY;
        this.randValueX = this.getRandom(2) + 1;
        this.randValueY = this.getRandom(2) + 1;
    }

    /**
     * Метод интерфейса Runnable
     * Данный метод вызывается при передаче экземпляра класса в конструктор класса Thread
     * Внутри себя реализовывает работу потока
     * По сути работает бесконечно, так как квадрат не может выйти за пределы
     */
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            this.move();
            try {
                Thread.sleep(20); // todo 50
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * Метод, высчитвающий случайное значение в диапозоне и возращающий его
     * @param bound диапозон значений
     * @return случайное число из диапозона
     */
    private int getRandom(int bound) {
        Random random = new Random(System.currentTimeMillis());
        int toRight = random.nextInt(bound);
        System.out.println(toRight);
        return toRight;
    }

    /**
     * Метод, осуществляющий передвижение квадрата (rectangle)
     * По дефолту перемещает квадрат под углом 45' в нижний правый угол.
     * При соприкосновлении с любой из граней ректангл меняет направление
     */
    private void move() {
        if (this.rect.getX() == 0) {
            this.valueX = 1;
            this.randValueX = this.getRandom(5);
        } else if (this.rect.getY() == 0) {
            this.valueY = 1;
            this.randValueY = this.getRandom(5);
        } else if (this.rect.getX() == this.limitX) {
            this.valueX = -1;
            this.randValueX = this.getRandom(5);
        } else if (this.rect.getY() == this.limitY) {
            this.valueY = -1;
            this.randValueY = this.getRandom(5);
        }
        this.rect.setX(this.rect.getX() + (this.randValueX * this.valueX));
        this.rect.setY(this.rect.getY() + (this.randValueY * this.valueY));
        //System.out.println("123");
    }
}