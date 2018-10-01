package ru.job4j.users;

import ru.job4j.crud.Store;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс для эмуляции работы хранилища данных
 * @author Galanov Sergey
 * @since 01.10.2018
 * @version 1.0
 */
public class ValidateStub {

    private final Map<Integer, User> store = new HashMap<>();
    private int ids = 0;


    public boolean add(User user) {
        user.setId(this.ids++);
        return !(this.store.put(user.getId(), user) == null);
    }


    public boolean update(int idOldUser, User user) {
        User userInStore = this.store.get(idOldUser);
        boolean result = false;
        if (userInStore != null) {
            userInStore.setName(user.getName());
            userInStore.setLogin(user.getLogin());
            userInStore.setEmail(user.getEmail());
            userInStore.setPassword(user.getPassword());
            result = true;
        }
        return result;
    }


    public boolean delete(int userId) {
        return !(this.store.remove(userId) == null);
    }


    public HashMap<Integer, User> findAll() {
        return (HashMap<Integer, User>) this.store;
    }


    public User findById(int id) {
        return this.store.get(id);
    }

    public boolean checkCorrect(String username, String password) {
        return false;
    }

    public Map<Integer, String> getAllRights() {
        return null;
    }

    public User checkUser(String userName, String userPassword) {
        return null;
    }

}
