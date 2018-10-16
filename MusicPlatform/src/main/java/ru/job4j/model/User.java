package ru.job4j.model;

import java.util.List;

public class User {

    private int id;
    private String name, login, password;
    private int role, finalAddress;
    List<Integer> musicTypes;
    List<Integer> address;

    public User(String name, String login, String password, int role) {
        this.id = name.hashCode() * 31 + login.hashCode();
        this.name = name;
        this.login = login;
        this.password = password;
        this.role = role;
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

    public List<Integer> getMusicTypes() {
        return musicTypes;
    }

    public void setMusicTypes(List<Integer> musicTypes) {
        this.musicTypes = musicTypes;
    }

    public List<Integer> getAddress() {
        return address;
    }

    public void setAddress(List<Integer> address) {
        this.address = address;
    }

    public int getFinalAddress() {
        return finalAddress;
    }

    public void setFinalAddress(int finalAddress) {
        this.finalAddress = finalAddress;
    }

}
