package ru.job4j.maps;

import java.util.HashMap;
import java.util.List;

/**
 * Класс, содержащий функцию перевода коллекции юзеров в хэшмапу
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.0
 */
public class UserConvert {

    /**
     * Метод переводит коллекцию юзеров в мапу
     * @param list - коллекция юзеров
     * @return хэшмапа, ключем каждого элемента коорого - его id
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<Integer, User>();
        for (User user : list) {
            result.put(user.getId(), user);
        }
        return result;
    }

}
