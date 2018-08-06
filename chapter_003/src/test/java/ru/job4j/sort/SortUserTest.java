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
 * @version 1.1
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

    /**
     * Тест функции сортировки по длине имени
     * на выходе должна получиться отсортированная коллекция по длине имени
     */
    @Test
    public void whenAddFourUsersThenSortByNameLength() {
        SortUser sorter = new SortUser();
        List<User> users = new ArrayList<User>();
        users.add(new User("Anna", 33));
        users.add(new User("Ivan", 24));
        users.add(new User("Michail", 51));
        users.add(new User("Sasha", 27));
        List<User> result = sorter.sortByNameLength(users);
        List<User> expect = new ArrayList<User>();
        expect.add(new User("Anna", 33));
        expect.add(new User("Ivan", 24));
        expect.add(new User("Sasha", 27));
        expect.add(new User("Michail", 51));
        assertThat(result.get(0).getName(), is(expect.get(0).getName()));
        assertThat(result.get(1).getName(), is(expect.get(1).getName()));
        assertThat(result.get(2).getName(), is(expect.get(2).getName()));
        assertThat(result.get(3).getName(), is(expect.get(3).getName()));
    }

    /**
     * Тест функции сортировки по всем полям
     * на выходе должна получиться коллекция, отсортированная сперва по имени, затем по возрасту
     * (только в тех случаях, где совпадает имя)
     */
    @Test
    public void whenAddUsersThenSortByAllFields() {
        SortUser sorter = new SortUser();
        List<User> users = new ArrayList<User>();
        users.add(new User("Anna", 25));
        users.add(new User("Ivan", 30));
        users.add(new User("Anna", 30));
        users.add(new User("Ivan", 25));
        List<User> result = sorter.sortByAllFields(users);
        List<User> expect = new ArrayList<User>();
        expect.add(new User("Anna", 25));
        expect.add(new User("Anna", 30));
        expect.add(new User("Ivan", 25));
        expect.add(new User("Ivan", 30));
        assertThat(result.get(0).getName(), is(expect.get(0).getName()));
        assertThat(result.get(1).getName(), is(expect.get(1).getName()));
        assertThat(result.get(2).getName(), is(expect.get(2).getName()));
        assertThat(result.get(3).getName(), is(expect.get(3).getName()));
        assertThat(result.get(0).getAge(), is(expect.get(0).getAge()));
        assertThat(result.get(1).getAge(), is(expect.get(1).getAge()));
        assertThat(result.get(2).getAge(), is(expect.get(2).getAge()));
        assertThat(result.get(3).getAge(), is(expect.get(3).getAge()));
    }
}
