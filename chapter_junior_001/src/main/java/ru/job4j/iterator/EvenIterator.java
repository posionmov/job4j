package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс, реализующий интерфейс Итератора
 * @author Galanov Sergey
 * @since 10.08.2018
 * @version 1.0
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
        boolean result = false;
        for (int i = curIndex; i < array.length; i++) {
            if (this.array[i] % 2 == 0) {
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Реализация метода интерфейса
     * ВОзращает текущее четное число и переводит указатель дальше до первого четного числа (или выбрасывает исключение)
     * @return текущее четное число
     */
    @Override
    public Object next() {
        while (this.array[curIndex] % 2 != 0) {
            curIndex++;
            if (curIndex == this.array.length) {
                throw new NoSuchElementException();
            }
        }
        return this.array[curIndex++];
    }
}
