package ru.job4j.models;

import java.util.List;

/**
 * Класс, описывающий модель пользователей сайтом
 * @author Galanov Sergey
 * @since
 * @version 1.0
 */
public class User {

    private int id;
    private String name;
    private String login;
    private String password;
    private Role role;
    private List<Advertisement> advertisements;

    public User() {

    }

    public User(int id) {
        this.id = id;
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + ", advertisements=" + advertisements + '}';
    }
}
