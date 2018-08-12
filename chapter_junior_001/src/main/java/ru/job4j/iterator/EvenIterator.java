package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий интерфейс Итератора
 * @author Galanov Sergey
 * @since 12.08.2018
 * @version 1.1
 */
public class EvenIterator implements Iterator {

    /**
     * Содержит внутренние поля
     */
    private final int[] array; // одномерный массив
    private int curIndex = 0; // Текущий индекс в массиве

    /**
     * Конструктор класса
     * @param array одномерный массив простых чисел
     */
    public EvenIterator(int[] array) {
        this.array = array;
    }

    /**
     * реализация метода интерфейса
     * @return  true - если последующий ряд чисел содержит четные чила
     *          false - ели последующие числа не содержат четных чисел
     */
    @Override
    public boolean hasNext() {
        return this.findInArray();
    }

    /**
     * Реализация метода интерфейса
     * Возращает текущее четное число и переводит указатель дальше до первого четного числа (или выбрасывает исключение)
     * @return текущее четное число
     */
    @Override
    public Object next() {
        if (!this.hasNext()) {
            throw new NoSuchElementException();
        }
        this.hasNext();
        return this.array[this.curIndex++];
    }

    /**
     * Вспомогательная функция поиска в массиве следующего четного числа
     * @return true - если следующее четное число есть, false - если следующего четного числа нет
     */
    public boolean findInArray() {
        boolean result = false;
        for (; curIndex < this.array.length; this.curIndex++) {
            if (this.array[curIndex] % 2 == 0) {
                result = true;
                break;
            }
        }
        return  result;
    }

}
