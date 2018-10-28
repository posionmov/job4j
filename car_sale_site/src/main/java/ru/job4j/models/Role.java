package ru.job4j.models;

/**
 * Класс, описывающий модель прав доступа
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class Role {

    /**
     * Поля класса:
     *      id
     *      описание (имя)
     */
    private int id;
    private String name;

    /**
     * Дефлоный конструктор класса
     */
    public Role() { }

    /**
     * Конструктор класса с полем id
     * @param id
     */
    public Role(int id) {
        this.id = id;
    }

    /**
     * Блок геттеров и сеттеров
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

    /**
     * Переопределенный метод toString()
     * @return строка, описывающая всет екущие поля обьекта данного класса
     */
    @Override
    public String toString() {
        return "Role{" + "id=" + id
                + ", name='" + name + '\'' + '}';
    }
}
