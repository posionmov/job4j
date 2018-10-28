package ru.job4j.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import ru.job4j.models.cars.Car;

import java.sql.Timestamp;

/**
 * Класс, описыващий модель обьявлений на сайте
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class Advertisement {

    /**
     * Поля класса:
     *      id
     *      описание (имя)
     *      дата создания
     *      статус закрытия (boolean)
     *      путь до картинки обьявления
     *      обьект класса User
     *      обьект класса Car
     */
    private int id;
    private String description;
    private Timestamp createDate;
    private boolean close;
    private String pathToImage;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Car car;

    /**
     * Дефлоный конструктор класса
     */
    public Advertisement() {

    }

    /**
     * Конструктор класса с полем id
     * @param id
     */
    public Advertisement(int id) {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public boolean getClose() {
        return close;
    }

    public void setClose(boolean close) {
        this.close = close;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isClose() {
        return close;
    }

    public String getPathToImage() {
        return pathToImage;
    }

    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
    }

    /**
     * Переопределенный метод toString()
     * @return строка, описывающая всет екущие поля обьекта данного класса
     */
    @Override
    public String toString() {
        return "Advertisement{" + "id=" + id
                + ", description='" + description + '\''
                + ", createDate=" + createDate
                + ", car=" + car
                + ", isClose=" + close + '}';
    }
}
