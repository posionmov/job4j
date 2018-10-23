package ru.job4j.models;

public class Body {

    private int id;
    private String description;
    private Car car;

    public Body() {

    }

    public Body(int id) {
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", car=" + car +
                '}';
    }
}
