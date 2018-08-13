package ru.job4j.generics;

/**
 * Интерфейс, описывающий какие методы должен содержать каждый класс, реализующий данный интерфейс
 * @param <T> любой класс являющийся наследником класса Base
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public interface Store<T extends Base> {

    void add(T model);

    boolean replace(String id, T model);

    boolean delete(String id);

    T findById(String id);

}
