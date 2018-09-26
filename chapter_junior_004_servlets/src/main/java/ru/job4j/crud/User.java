package ru.job4j.crud;

import java.util.Date;

/**
 * Класс, описывающий модель данных
 * @author Galanov Sergey
 * @since 26.09.2018
 * @version 1.2
 */
public class User {

    /**
     * Приватные поля класса
     * Содержат информацию о пользователе
     */
    private int id, right;
    private String name, login, email, password, createDate;

    /**
     * Конструктор класса
     * @param name - имя нового пользователя
     * @param login - логин нового пользователя
     * @param email - пароль нового пользователя
     * @param right - права доступа у пользователя
     * @param password - пароль пользователя
     */
    public User(String name, String login, String email, int right, String password) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date().toString();
        this.id = this.hashCode();
        this.right = right;
        this.password = password;
    }

    /**
     * Метод, проверяющий равны ли два обьекта
     * @param otherUser - обьект класса object с которым сравнивается данный обьект
     * @return true если такой пользователь уже есть
     */
    @Override
    public boolean equals(Object otherUser) {
        boolean result = false;
        if (this.getId() == ((User) otherUser).getId()
                && this.getName().equals(((User) otherUser).getName())
                && this.getLogin().equals(((User) otherUser).getLogin())
                && this.getEmail().equals(((User) otherUser).getEmail())
                && this.getRight() == ((User) otherUser).getRight()
        ) {
            result = true;
        }
        return result;
    }

    /**
     * Переопределенный метод, вычисляющий хэшкод обьекта данного класса
     * @return
     */
    @Override
    public int hashCode() {
        int result = (this.name != null) ? this.name.hashCode() : 0;
        result = 31 * result + this.login.hashCode();
        result = 31 * result + this.email.hashCode();
//        result = 31 * result + this.right.hashCode();
        return result;
    }

    /**
     * Блок геттеров и сеттеров полей класса
     * @return
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public String getCreateDate() {
        return createDate;
    }

    public int getRight() {
        return right;
    }

    public void setId(int newId) {
        this.id = newId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
