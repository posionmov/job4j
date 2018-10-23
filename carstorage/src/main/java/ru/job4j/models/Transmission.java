package ru.job4j.models;

public class Transmission {
    private int id;
    private String description;

    public Transmission() {

    }

    public Transmission(int id) {
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

    @Override
    public String toString() {
        return "Transmission{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
