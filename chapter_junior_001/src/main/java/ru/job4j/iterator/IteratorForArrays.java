package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий интерфейс Итератора
 * @author Galanov Sergey
 * @since 10.08.2018
 * @version 1.1
 */
public class IteratorForArrays implements Iterator {

    /**
     * Содерит внутренние поля
     */
    private int curIndexI = 0; // Текущий индекс по Х
    private int curIndexJ = 0; // Текущий индекс по У
    private final int[][] array; // Двумерный массив

    /**
     * Конструктор класса
     * @param array - двумерный массив значений
     * выкидывает исключение " NoSuchElementException " если массив пустой
     */
    public IteratorForArrays(int[][] array) {
        this.array = array;
    }

    /**
     * Реализация метода интерфейса
     * Возращает true при условии, что текущий индекс по Х больше или равен длине
     * @return true - если есть след элемент, false если следующего элемента нет
     */
    @Override
    public boolean hasNext() {
        boolean result = true;
        if (this.curIndexI >= this.array.length) {
            result = false;
        }
        return result;
    }

    /**
     * Реализация метода интерфейса
     * Возращает текущее значение из массива и передвигает указатель дальше:
     *                                      - если по текущий указатель У меньше максимальной длиты текущего массива - 1,
     *                                                  то передвигает указатель по У на 1 пункт
     *                                      - иначе двигает указатель по Ч на 1 пункт, указатель на У устанавливает на первый элемент
     * @return
     */
    @Override
    public Object next() {
        int result;
        if (!this.hasNext()) { // Выкидывание ошибки если в массиве нет элементов, но идет попытка вернуть элемент
            throw new NoSuchElementException();
        }
        if (this.curIndexJ < this.array[this.curIndexI].length - 1) {
            result = this.array[this.curIndexI][this.curIndexJ++];
        } else {
            result = this.array[this.curIndexI++][this.curIndexJ];
            this.curIndexJ = 0;
        }

        return result;
    }
}
