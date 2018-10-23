package ru.job4j.models;

/**
 * Класс, описывающий модель прав доступа
 * @author Galanov Sergey
 * @since
 * @version 1.0
 */
public class Role {

    private int id;
    private String name;

    public Role() { }

    public Role(int id) {
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

    @Override
    public String toString() {
        return "Role{" + "id=" + id
                + ", name='" + name + '\'' + '}';
    }
}
