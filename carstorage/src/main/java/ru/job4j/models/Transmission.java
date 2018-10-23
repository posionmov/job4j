package ru.job4j.models;

import javax.persistence.*;

/**
 * Класс-модель коробки передач
 * @author Galanov Sergey
 * @since 23.10.2018
 * @version 1.0
 */
@Entity
@Table(name = "transmissions")
public class Transmission {

    /**
     * Приватные поля класса
     * Содержат информацию о
     *      id кузова
     *      описание коробки передач
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "descr")
    private String description;

    /**
     * Дефолтный конструктор класса
     */
    public Transmission() {

    }

    /**
     * Конструктор класса с параметром id
     * @param id
     */
    public Transmission(int id) {
        this.id = id;
    }

    /**
     * Блок геттеров и сетеров
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Переопределенный метод, переводящий обьект в строку
     * @return строка, содержащая в себе все поля данного обьекта
     */
    @Override
    public String toString() {
        return "Transmission{" + "id=" + id + ", description='" + description + '\'' + '}';
    }
}
