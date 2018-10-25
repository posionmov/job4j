package ru.job4j.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Car car;

    private boolean close;

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

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", createDate=" + createDate +
                ", car=" + car +
                ", isClose=" + close +
                '}';
    }
}
