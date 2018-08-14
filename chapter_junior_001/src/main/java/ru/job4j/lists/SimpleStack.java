package ru.job4j.lists;

/**
 * Класс-обертка над оберткой связанного списка DynamicArray
 * Представляет собой стек. Элементы в нем возращаются по принципу ЛИФО (Последний зашел - Первый вышел)
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 * @param <E>
 */
public class SimpleStack<E> {

    /**
     * Приватные поля класса
     */
    private DynamicArrayLinkedList<E> list = new DynamicArrayLinkedList<E>(); // обьект класса-обертки связанного списка
    private int sizeOfList = 0; // текущее количество элементов в списке

    /**
     * Метод, возращающий последний добавленный элемент
     * @return последний добавленный элемент
     */
    public E poll() {
        E result = this.list.get(this.sizeOfList - 1);
        this.list.delete(sizeOfList - 1);
        this.sizeOfList--;
        return result;
    }

    /**
     * Метод, добавляющий элемент в список
     * @param object добавляемый элемент
     */
    public void push(E object) {
        this.list.add(object);
        this.sizeOfList++;
    }

}
