package ru.job4j.tree;

import java.util.Optional;

/**
 * Интерфейс реализации имитации древа
 * @author Galanov Sergey
 * @since 17.08.2018
 * @version 1.0
 * @param <E> любой класс, реализующий интерфейс Comporable
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E children);

    Optional<Node<E>> findBy(E value);

}
