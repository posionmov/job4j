package ru.job4j.generics;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Класс, который может содержать генерики, а также реализует интерфейс Iterable,
 * который позволяет создавать итератор
 * @param <T> - любой ссылочный тип данных
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class SimpleArray<T> implements Iterable<T> {

    /**
     * СОдержит внутренние поля
     */
    private Object[] array; // массив обьектов типа Object
    private int curIndex = 0; // текущий индекс

    /**
     * Конструктор данного класса
     * @param size размер создаваемого массива
     */
    public SimpleArray(int size) {
        this.array = new Object[size];
    }

    /**
     * Добавлет в текущий массив по индексу новый обьект
     * Перед этим проверяет на то, есть ли в текущем массиве обьект по данному индексу
     * @param model - втсаляемый обьект
     */
    public void add(T model) {
        checkIndex(-1);
        this.array[curIndex++] = model;
    }

    /**
     * Метод меняет значение в массиве по индексу
     * Сперва проверяет имеется ли такой индекс в массиве
     * @param index индекс, по которому необходимо вставить новое значение
     * @param model вставляемое значение
     */
    public void set(int index, T model) {
        checkIndex(index);
        this.array[index] = model;

    }

    /**
     * Метод, удаляющий элементы из массива
     * Сперва идет по всему массиву и ищет элемент, который равен передаваемому в метод значению
     * Затем все элементы правее перемещает на один элемент левее
     * Последний элемент приравнивается к null
     * @param model - удалемое значение
     */
    public void delete(T model) {
        for (int i = 0; i < this.array.length; i++) {
            if (this.array[i].equals(model)) {
                System.arraycopy(this.array, i + 1, this.array, i, this.array.length - 1 - i);
                this.array[this.array.length - 1] = null;
                break;
            }
        }
    }

    /**
     * Метод, который возращает значение из массива по индексу
     * Сперва проверяет имеется ли данный данный индекс в массиве (те не выходит ли он за его пределы)
     * @param index - индекс элемента, которого мы хотим вернуть
     * @return значение элемента из массиво по индексу
     */
    public T get(int index) {
        this.checkIndex(index);
        return (T) this.array[index];
    }

    /**
     * Вспомогательный метод, проверяющий валидатность иискомого индекса
     * @param index - индекс для проверки
     */
    private void checkIndex(int index) {
        int checkingIndex = (index >= 0) ? index : this.curIndex;
        if (checkingIndex >= this.array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Метод интерфейса Iterable
     * Позволяет делать интераторы для данного массива
     * @return итератор
     */
    @Override
    public Iterator<T> iterator() {

        Iterator<T> iterator = new Iterator<T>() {

            /**
             * Содержит приватные поля
             */
            private int curIndex = 0; // Текущий индекс
            private final int curLength = array.length; // Длина массива

            /**
             * Метод, проверяющий есть ли следующий элемент
             * @return true если в массиве дальше есть еще элемент
             */
            @Override
            public boolean hasNext() {
                return checkNext();
            }

            /**
             * Метод, возращающий текущее значение и переводящий указатель дальше
             * @return текущее значение
             */
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return (T) array[curIndex++];
            }

            /**
             * Вспомогательный метод проверки следующего элемента
             * @return true если дальше есть еще элемент
             */
            private boolean checkNext() {
                boolean hasNext = false;
                for (; curIndex < curLength; curIndex++) {
                    if (array[curIndex] != null) {
                        hasNext = true;
                        break;
                    }
                }
                return hasNext;
            }
        };
        return iterator;
    }
}
