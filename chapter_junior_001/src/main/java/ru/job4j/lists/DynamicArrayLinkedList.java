package ru.job4j.lists;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Класс для реализации связанного списка
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 * @param <E> любой обьект класса E
 */
public class DynamicArrayLinkedList<E> implements Iterable<E> {

    /**
     * Содержит внутренние поля класса
     */
    private int mods = 0; // Счеткик изменений в связанном списке
    private LinkedObject<E> previous; // Ссылка на текущий обьект
    private int size = 0; // Текущий размер последовательности

    /**
     * Метод добавления элемента в последовательность
     * @param data - обьект класса E, который необходимо поместить в последовательность
     */
    public void add(E data) {
        LinkedObject<E> newLink = new LinkedObject<>(data);
        newLink.nextObject = this.previous;
        this.previous = newLink;
        this.mods++;
        this.size++;
    }

    /**
     * Метод получения элемента из последовательности по индексу
     * @param index - индекс в последовательности
     * @return значение элемента из последовательности по индексу
     */
    public E get(int index) {
        LinkedObject<E> result = this.previous;
        for (int i = this.size; i > index + 1; i--) {
            result = result.nextObject;
        }
        return result.data;
    }

    /**
     * Класс, реализующий хранение данных и ссылки на предыдущий обьект класса
     * @param <e>
     */
    private class LinkedObject<e> {
        E data;
        LinkedObject<e> nextObject;
        LinkedObject(E data) {
            this.data = data;
        }
    }

    /**
     * Реализация метода интерфейса
     * @return Обьект класса Iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            /**
             * СОдержит внутренние поля класса
             */
            private final int curMods = mods; // количество изменений в последовательности на момент создания итератора
            private int curIndex = 0; // Текущий индекс

            /**
             * Метод, проверящий есть ли еще обьекты далее
             * Так же выкидывает ошибку если в последовательности сделали изменения после создания итератора
             * @return true если в последовательности есть следующий элемент
             */
            @Override
            public boolean hasNext() {
                boolean result = false;
                if (this.curMods != mods) {
                    throw new ConcurrentModificationException();
                }
                for (; this.curIndex < size; this.curIndex++) {
                    if (get(this.curIndex) != null) {
                        result = true;
                        break;
                    }
                }
                return result;
            }

            /**
             * Метод, возращающий текущий элемент, и переводящий указатель дальше
             * Выкидывает исключение если дольше элементов нет
             * @return текущий обьект из последовательности
             */
            @Override
            public E next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return get(this.curIndex++);
            }
        };
    }
}
