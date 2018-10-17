package ru.job4j.model;
import java.util.List;

/**
 * Класс-модель для пользователей
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class User {

    /**
     * Поля класса
     * Содержат в себе:
     *      - id пользователя
     *      - имя, логин и пароль пользователя
     *      - права пользователя
     *      - коллекцию типов музыки пользователя
     *      - коллекцию, содержащую полный адрес пользователя
     */
    private int id;
    private String name, login, password;
    private int role;
    private List<Integer> musicTypes;
    private List<Integer> address;

    /**
     * Конструктор данного класса
     * @param name - имя
     * @param login - логин
     * @param password - пароль
     * @param role - права доступа
     */
    public User(String name, String login, String password, int role) {
        this.id = name.hashCode() * 31 + login.hashCode();
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    /**
     * Блок гетеров и сетеров для полей данной модели пользователя
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Integer> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(List<Integer> musicTypes) {
        this.musicTypes = musicTypes;
    }

    public List<Integer> getAddress() {
        return address;
    }

    public void setAddress(List<Integer> address) {
        this.address = address;
    }

}
