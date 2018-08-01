package ru.job4j.professions;

public class Patient {

    private String name;
    private String symptoms;

    public Patient(String name, String symptoms) {
        this.name = name;
        this.symptoms = symptoms;
    }

    public String getSymptoms() {
        return this.symptoms;
    }
}
