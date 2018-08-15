package ru.job4j.sets;

import ru.job4j.lists.DynamicArrayLinkedList;
import java.util.Iterator;

/**
 * Класс, имитирующий сет на основе связанного списка
 * @author Galanov Sergey
 * @since 15.08.2018
 * @version 1.0
 * @param <E> любой класс
 */
public class SimpleLinkedBaseSet<E> implements Iterable<E> {

    /**
     * Приватные поля класса
     * Содержит проинициализированную переменную класса DynamicArrayLinkedList
     */
    private DynamicArrayLinkedList<E> set = new DynamicArrayLinkedList<E>();

    /**
     * Метод, добаляющий элемент в список
     * Производит операцию только в том случае, если данного элемента нет в списке
     * @param element - элемент класса E, который требуется добавить в импровизированный сет
     */
    public void add(E element) {
        if (this.checkUnique(element)) {
            this.set.add(element);
        }
    }

    public int getSize() {
        return this.set.getLength();
    }

    /**
     * Метод, проверящий уникальность элемента
     * @param element - элемент, уникальность которого требуется проверить
     * @return true если элемент уникальный
     */
    private boolean checkUnique(E element) {
        boolean result = true;
        int cutIndex = 0;
        Iterator iterator = this.set.iterator();
        while (iterator.hasNext()) {
            if (this.set.get(cutIndex++) == element) {
                result = false;
                break;
            }
            iterator.next();
        }
        return result;
    }

    /**
     * Реализация интерфейса Iterable
     * Позволяет создать обьект класса Iterator<E> благодаря котормоу можно пройтись по импровизированному сету
     * @return
     */
    @Override
    public Iterator<E> iterator() {
        Iterator<E> setIterator = this.set.iterator();
        return new Iterator<E>() {
            @Override
            public boolean hasNext() {
                return setIterator.hasNext();
            }

            @Override
            public E next() {
                return setIterator.next();
            }
        };
    }
}
