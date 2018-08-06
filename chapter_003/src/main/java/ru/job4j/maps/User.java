package ru.job4j.maps;

public class User {

    private String name;
    private int id;
    private String city;

    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

}
