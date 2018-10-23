package ru.job4j.models;

import ru.job4j.models.cars.Car;

import java.sql.Timestamp;

/**
 * Класс, описыващий модель обьявлений на сайте
 * @author Galanov Sergey
 * @since
 * @version 1.0
 */
public class Advertisement {

    private int id;
    private String description;
    private Timestamp createDate;
    private User creator;
    private Car car;
    private boolean isClose;

    public Advertisement() {

    }

    public Advertisement(int id) {
        this.id = id;
    }

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

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public boolean isClose() {
        return isClose;
    }

    public void setClose(boolean close) {
        isClose = close;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Advertisement{" + "id=" + id
                + ", description='" + description + '\''
                + ", createDate=" + createDate
                + ", creator=" + creator
                + ", car=" + car
                + ", isClose=" + isClose + '}';
    }
}
