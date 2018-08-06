package ru.job4j.sort;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для сортировки коллекции
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.0
 */
public class SortUser {

    /**
     * Функция, возвращающая отсортированнный сет юзеров
     * @param users - коллекция юзеров
     * @return отсортированный сет юзеров
     */
    public Set<User> sort(List<User> users) {
        return new TreeSet<User>(users);
    }
}
