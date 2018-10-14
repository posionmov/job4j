package ru.job4j.model;

public class User {

    private int id;
    private String name, login, password;
    private int role, address, musicType;

    public User(String name, String login, String password, int role, int musicType) {
        this.id = name.hashCode() * 31 + login.hashCode();
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
        this.musicType = musicType;
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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    public int getMusicType() {
        return musicType;
    }

    public void setMusicType(int musicType) {
        this.musicType = musicType;
    }
}
