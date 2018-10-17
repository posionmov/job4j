package ru.job4j.storage;

import ru.job4j.model.User;

import java.util.List;
import java.util.Map;

/**
 * Синглтон для работы с БД
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public enum  StoreEnum implements Store {
    INSTANCE;

    /**
     * Метод добавления пользователя
     * @param user
     * @return
     */
    @Override
    public boolean addUser(User user) {
        return DbStorage.INSTANCE.addUser(user);
    }

    /**
     * Метод получения всех прав доступа
     * @return
     */
    @Override
    public Map<Integer, String> getAllRoles() {
        return DbStorage.INSTANCE.getAllRoles();
    }

    /**
     * Метод получения всех типов музыки
     * @return
     */
    @Override
    public Map<Integer, String> getAllTypes() {
        return DbStorage.INSTANCE.getAllTypes();
    }

    /**
     * Метод получения всех адресов
     * @return
     */
    @Override
    public Map<Integer, Map<String, Map<Integer, Map<String, Map<Integer, String>>>>> getAllAddresses() {
        return DbStorage.INSTANCE.getAllAddresses();
    }

    /**
     * Метод получения юзеров
     * @param type - типа запроса
     * @param address - коллекция адресов
     * @param role - искомое право доступа
     * @param types - коллекция типов музыки
     * @param stringForSearch - строка для поиска
     * @return коллекция юзеров
     */
    @Override
    public List<User> getUsers(String type, List<Integer> address, int role, List<Integer> types, String stringForSearch) {
        return DbStorage.INSTANCE.getUsers(type, address, role, types, stringForSearch);
    }

    /**
     * Метод получения пользователя по id
     * @param id
     * @return
     */
    @Override
    public User getUserById(int id) {
        return DbStorage.INSTANCE.getUserById(id);
    }

    /**
     * Метод обновления пользователя
     * @param newUser
     * @return
     */
    @Override
    public boolean updateUser(User newUser) {
        return DbStorage.INSTANCE.updateUser(newUser);
    }

    /**
     * Метод удаления пользователя
     * @param userId
     * @return
     */
    @Override
    public boolean deleteUser(int userId) {
        return DbStorage.INSTANCE.deleteUser(userId);
    }

    /**
     * Метод проверки наличия пользователя по логину и паролю (необходимо для авторизации)
     * @param login
     * @param password
     * @return
     */
    @Override
    public User checkUser(String login, String password) {
        return DbStorage.INSTANCE.checkUser(login, password);
    }
}
