package ru.job4j.models.cars;

import java.util.List;

public class CarMark {

    private int id;
    private String descr;
    private List<CarModel> models;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public List<CarModel> getModels() {
        return models;
    }

    public void setModels(List<CarModel> models) {
        this.models = models;
    }

    @Override
    public String toString() {
        return "CarMark{" +
                "id=" + id +
                ", descr='" + descr + '\'' +
                ", models=" + models +
                '}';
    }
}
