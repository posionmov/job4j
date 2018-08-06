package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Класс для сортировки коллекции
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.1
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

    /**
     * Функция сортирует коллекцию по длине имени каждого пользователя
     * @param list - неотсортированная по имени коллекция
     * @return отсортированная коллекция по длине имени
     */
    public List<User> sortByNameLength(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        return Integer.compare(o1.getName().length(), o2.getName().length());
                    }
                });
        return list;
    }

    /**
     * Функция сортировки коллекции по имени и ворзасту
     * по возрасту сортируется только в том случае, если имя абсолютно совпадает
     * @param list неотсортированная коллекция
     * @return отсортированная коллекция
     */
    public List<User> sortByAllFields(List<User> list) {
        list.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getName().equals(o2.getName()) ?  Integer.compare(o1.getAge(), o2.getAge())
                                                            : o1.getName().compareTo(o2.getName());
            }
        });
        return list;
    }
}
