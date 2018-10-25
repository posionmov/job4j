package ru.job4j.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

    @JsonIgnore
    private String login;

    @JsonIgnore
    private String password;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Role role;

    @JsonIgnore
    private List<Advertisement> advertisements;

    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id
                + ", name='" + name + '\''
                + ", login='" + login + '\''
                + ", password='" + password + '\''
                + ", role=" + role
                + ", advertisements=" + advertisements
                + ", email='" + email + '\'' + '}';
    }
}
