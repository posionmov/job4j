package ru.job4j.models.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class CarModel {
    private int id;
    private String  descr;

    @JsonIgnore
    private CarMark mark;

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

    public CarMark getMark() {
        return mark;
    }

    public void setMark(CarMark mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return "CarBodyType{" + "id=" + id
                + ", descr='" + descr + '\'' + '}';
    }
}
