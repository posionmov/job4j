package ru.job4j.lists;

/**
 * Класс-обертка над оберткой связанного списка DynamicArray
 * Представляет собой очередь. Элементы в нем возращаются по принципу ФИФО (Первый зашел - Первый вышел)
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.0
 * @param <E>
 */
public class SimpleQueue<E> {

    /**
     * Содержит приватные поля
     */
    private DynamicArrayLinkedList<E> list = new DynamicArrayLinkedList<E>(); // ссылка на обьект класса-оертки списка

    /**
     * Метод, возращающий первый в очереди обьект класса Е
     * @return обьект класса Е
     */
    public E poll() {
        E result = this.list.get(0);
        this.list.delete(0);
        return result;
    }

    /**
     * Метод, помещающий в очередь элемент Е
     * @param object - обьект для помещения в очередь
     */
    public void push(E object) {
        this.list.add(object);
    }

}
