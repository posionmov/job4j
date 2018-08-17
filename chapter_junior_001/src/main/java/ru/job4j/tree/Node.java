package ru.job4j.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс-оболочка массива
 * @author Galanov Sergey
 * @since 17.08.2018
 * @version 1.0
 * @param <E>
 */
public class Node<E extends Comparable<E>> {

    /**
     * Содержит приватные поля класса
     */
    private final List<Node<E>> children = new ArrayList<>(); // Массив детей
    private final E value; // Значение родителя

    /**
     * Конструктор данного класса
     * @param value Значение, которое записывается в поле класса
     */
    public Node(final E value) {
        this.value = value;
    }

    /**
     * Добавление нового элемента в массив детей
     * @param child добавляемый ребенок
     */
    public void add(Node<E> child) {
        this.children.add(child);
    }

    /**
     * Метод, возращающий список всех детей
     * @return список всех детей
     */
    public List<Node<E>> leaves() {
        return this.children;
    }

    /**
     * Метод, проверяющий равенство двух значений
     * @param another значение элемента
     * @return true если элементы равны
     */
    public boolean eqValue(E another) {
        return this.value.compareTo(another) == 0;
    }
}
