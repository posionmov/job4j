package ru.job4j.models;

public class Engine {
    private int id;
    private String description;

    public Engine() {

    }

    public Engine(int id) {
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
        return "Engine{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
