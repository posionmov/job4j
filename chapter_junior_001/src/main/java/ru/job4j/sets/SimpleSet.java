package ru.job4j.sets;

import ru.job4j.lists.DynamicArrayList;
import java.util.Iterator;

/**
 * Класс, имитирующий работу Сетов
 * @author Galanov Sergey
 * @since 15.08.2018
 * @version 1.0
 * @param <E> Любой класс
 */
public class SimpleSet<E> implements Iterable<E> {

    /**
     * Приватные пол класса
     * Содержит только инициализацию обьекта класса DynamicArrayList
     */
    private DynamicArrayList<E> list = new DynamicArrayList<E>();

    /**
     * Метод добавления элемента в массив
     * Осуществляет добавление только при условии того, что такого элемента в массиве нет
     * @param data элемент класса У, который необходимо пометить в массив
     */
    public void add(E data) {
        if (this.checkUnique(data)) {
            this.list.add(data);
        }
    }

    /**
     * Метод, возращающий текущий размер массива
     * @return текущий размер массива
     */
    public int getSize() {
        return list.getSize();
    }

    /**
     * Вспомогательный метод, проверяющий уникальность элемента в массиве
     * Пробегается по массиву при помощи итератора
     * @param data данные, нахождение которых в массиве требуется доказать
     * @return false если такие же данные еть в массиве
     */
    private boolean checkUnique(E data) {
        boolean unique = true;
        int curIndex = 0;
        Iterator<E> iterator = this.list.iterator();
        while (iterator.hasNext()) {
            if (this.list.get(curIndex++).equals(data)) {
                unique = false;
                break;
            }
            iterator.next();
        }
        return unique;
    }

    /**
     * Реализация интерфейса Iterable
     * Позволяет проходиться по данному массиву
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> listIterator = this.list.iterator();
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return listIterator.hasNext();
            }
            @Override
            public E next() {
                return listIterator.next();
            }
        };
    }
}
