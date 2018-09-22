package ru.job4j.crud;

import java.util.Date;

/**
 * Класс, описывающий модель данных
 * @author Galanov Sergey
 * @since 18.09.2018
 * @version 1.1
 */
public class User {

    /**
     * Приватные поля класса
     * Содержат информацию о пользователе
     */
    private int id;
    private String name, login, email;
    private String createDate;

    /**
     * Конструктор класса
     * @param name - имя нового пользователя
     * @param login - логин нового пользователя
     * @param email - пароль нового пользователя
     */
    public User(String name, String login, String email) {
        this.name = name;
        this.login = login;
        this.email = email;
        this.createDate = new Date().toString();
        this.id = this.hashCode();
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
                && this.getEmail().equals(((User) otherUser).getEmail())) {
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

}
