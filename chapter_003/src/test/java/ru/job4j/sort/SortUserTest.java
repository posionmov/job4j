package ru.job4j.sort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Класс для тестирования сортировки в классе SortUser
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.0
 */
public class SortUserTest {

    /**
     * Тест при условии добавления четырех не сортированных элементов
     * На выходе должен получиться отсортированный по возрасту сет
     */
    @Test
    public void whenAddFourNotSortedUserThenSort() {
        SortUser sorter = new SortUser();
        List<User> users = new ArrayList<User>();
        users.add(new User("Anna", 33));
        users.add(new User("Ivan", 24));
        users.add(new User("Michail", 51));
        users.add(new User("Sasha", 27));
        Set<User> result = sorter.sort(users);
        Set<User> expect = new TreeSet<>();
        expect.add(new User("Ivan", 24));
        expect.add(new User("Sasha", 27));
        expect.add(new User("Anna", 33));
        expect.add(new User("Michail", 51));
        assertThat(result, is(expect));
    }
}
