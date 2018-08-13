package ru.job4j.lists;

/**
 * Класс, который имитирует работу LinkedList
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.1
 * @param <E>
 */
public class SimpleArrayList<E> {

    /**
     * Поля данного класса
     */
    private int size; // текущий размер последовательности
    private Node<E> first; // ссылка на последний элемент последовательности

    /**
     * Метод добавления нового элемента в последовательность
     * @param date значение, которое необходимо вставить в последовательность
     */
    public void add(E date) {
        Node<E> newLink = new Node<>(date);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }

    /**
     * Метод удаления первого элемента в списке
     * @return удаленный элемент
     */
    public E delete() {
        Node<E> element = this.first;
        E result = null;
        for (int i = 0; i < this.size; i++) {
            if (i == this.size - 2) {
                result = element.next.date;
                element.next = element.next.next;
                size--;
                break;
            }
            element = element.next;
        }
        return result;
    }

    /**
     * Метод получения элемента по индексу
     * @param index
     * @return
     */
    public E get(int index) {
        return findInArray(index);
    }

    /**
     * Метод получения текущего размера последовательности
     * @return
     */
    public int getSize() {
        return this.size;
    }

    /**
     * внутренний класс, описывающий связь обьектов
     * @param <E>
     */
    private class Node<E> {
        /**
         * Поля класса, они доступны извне
         */
        E date;
        Node<E> next;

        /**
         * Конструктор класса
         * @param date
         */
        Node(E date) {
            this.date = date;
        }
    }

    /**
     * Вспомогательный метод, который ищет значение в последовательности
     * @param index
     * @return
     */
    private E findInArray(int index) {
        Node<E> element = this.first;
        for (int i = 0; i < index; i++) {
            element = element.next;
        }
        return element.date;
    }

}
