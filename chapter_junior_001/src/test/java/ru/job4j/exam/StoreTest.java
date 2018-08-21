package ru.job4j.exam;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Класс для тестирования задания по поиску произведенных изменений с массивом
 * @author Galanov Sergey
 * @since 21.08.2018
 * @version 1.0
 */
public class StoreTest {

    /**
     * Содержит приватные полня
     * Тут расположены константы строк, которыми удобно пользоваться при получении знаений
     * из получаемой мапы
     */
    private final static String CHANGES = "Изменено";
    private final static String DELETE = "Удалено";
    private final static String ADD = "Добавлено";

    /**
     * Тест если 1 удаление, 1 изменение и одно добавление в массив
     */
    @Test
    public void whenOneAddOneDeleteAndOneChangeThenReturnCurrentAnswer() {
        Store store = new Store();
        List<User> userOne = new ArrayList<>();
        userOne.add(new User(1, "One"));
        userOne.add(new User(2, "Two"));
        userOne.add(new User(3, "Three"));
        userOne.add(new User(4, "Four"));

        List<User> userTwo = new ArrayList<>();
        userTwo.add(new User(2, "Two"));
        userTwo.add(new User(3, "Three1"));
        userTwo.add(new User(4, "Four"));
        userTwo.add(new User(5, "Five"));

        Map<String, Integer> result = store.diff(userOne, userTwo);
        assertThat(result.get(CHANGES), is(1));
        assertThat(result.get(DELETE), is(1));
        assertThat(result.get(ADD), is(1));
    }

    /**
     * Тест если только 1 удаление
     */
    @Test
    public void whenOnlyDeleteThenReturnCurrentAnswer() {
        Store store = new Store();
        List<User> userOne = new ArrayList<>();
        userOne.add(new User(1, "One"));
        userOne.add(new User(2, "Two"));
        userOne.add(new User(3, "Three"));
        userOne.add(new User(4, "Four"));

        List<User> userTwo = new ArrayList<>();
        userTwo.add(new User(2, "Two"));
        userTwo.add(new User(3, "Three"));
        userTwo.add(new User(4, "Four"));

        Map<String, Integer> result = store.diff(userOne, userTwo);
        assertThat(result.get(CHANGES), is(0));
        assertThat(result.get(DELETE), is(1));
        assertThat(result.get(ADD), is(0));
    }

    /**
     * Тест если только изменение и добавление
     */
    @Test
    public void whenChangeAndAddThenReturnCurrentAnswer() {
        Store store = new Store();
        List<User> userOne = new ArrayList<>();
        userOne.add(new User(1, "One"));
        userOne.add(new User(2, "Two"));
        userOne.add(new User(3, "Three"));
        userOne.add(new User(4, "Four"));

        List<User> userTwo = new ArrayList<>();
        userTwo.add(new User(1, "One"));
        userTwo.add(new User(2, "Two"));
        userTwo.add(new User(3, "Three3"));
        userTwo.add(new User(4, "Four"));
        userTwo.add(new User(5, "Five"));

        Map<String, Integer> result = store.diff(userOne, userTwo);
        assertThat(result.get(CHANGES), is(1));
        assertThat(result.get(DELETE), is(0));
        assertThat(result.get(ADD), is(1));
    }
}