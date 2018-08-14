package ru.job4j.lists;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс-обертка массива, придающий ему динамичность
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.1
 * @param <E> - любой класс
 */
public class DynamicArrayList<E> implements Iterable<E> {

    /**
     * Содержит внутренние поля класса
     */
    private Object[] container = new Object[10]; // Массив. По дефолту имеет размер в 10 элементов. В дальнейшем изменяется
    private int curLeght = 0; // Текущее количество элементов в массиве
    private int curMod = 0; // Текущее количество изменений в массиве

    /**
     * Метод добавления эемента в массив
     * Так же при добавлении элемента в полный массив создает новый массив с длиной на 1 больше
     * @param object обьект класса E, который необходимо поместить в массив
     */
    public void add(E object) {
        if (curLeght == this.container.length) {
            this.growLength();
        }
        this.container[curLeght++] = object;
        this.curMod++;
    }

    /**
     * Метод, который увеличивает длину текущего массива
     */
    private void growLength() {
        this.container = Arrays.copyOf(this.container, (int) (curLeght * 1.5));
    }

    /**
     * Метод получения значения элемента из массива по индексу
     * @param index - индекс элемента, значение которого необходимо вернуть
     * @return значение элемента из массива по индексу
     */
    public E get(int index) {
        return (E) this.container[index];
    }

    /**
     * Реализация метода интерфейса для создания обьекта класса Iterator
     * @return обьект класса Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /**
             * Содержит поля анонимного класса
             */
            private final int mods = curMod; // записывает текущее количество изменений в массиве
            private int curIndex = 0; // текущий индекс для итератора

            /**
             * Метод, проверяющий есть ли следующий элемент
             * @return true если есть
             */
            @Override
            public boolean hasNext() {
                return this.isNext();
            }

            /**
             * Метод, возращающий текущий элемент и переводящий указатель на следующий
             * Так же выкидывает исключение если следующего элемента нет
             * @return текущий элемент класса E
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (E) container[this.curIndex++];
            }

            /**
             * Вспомогательный метод, проходящий по всему массиву
             * Так же выкидывает исключение, если после создания итератора в массиве появились изменения
             * @return true если следующее значение есть
             */
            private boolean isNext() {
                if (this.mods != curMod) {
                    throw new ConcurrentModificationException();
                }
                boolean result = false;
                for (; this.curIndex < curLeght; this.curIndex++) {
                    if (container[this.curIndex] != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }
        };
    }
}
