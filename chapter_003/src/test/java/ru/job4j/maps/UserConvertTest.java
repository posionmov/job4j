package ru.job4j.maps;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

/**
 * Тест для UserConverter.java
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.0
 */
public class UserConvertTest {

    /**
     * Тест, проверяющий перевод коллекции в хэшмапу
     */
    @Test
    public void whenAddTwoUserThenCreateHashMap() {
        UserConvert converter = new UserConvert();
        List<User> users = new ArrayList<User>();
        users.add(new User(0, "Ivan", "Moscow"));
        users.add(new User(2, "Sasha", "Rostov"));
        users.add(new User(1, "Michail", "SPB"));
        HashMap<Integer, User> result = converter.process(users);

        HashMap<Integer, User> expect = new HashMap<>();
        expect.put(0, new User(0, "Ivan", "Moscow"));
        expect.put(2, new User(2, "Sasha", "Rostov"));
        expect.put(1, new User(1, "Michail", "SPB"));
        assertThat(result.get(2).getName(), is(expect.get(2).getName()));
    }
}
