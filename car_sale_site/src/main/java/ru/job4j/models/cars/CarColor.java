package ru.job4j.models.cars;

public class CarColor {
    private int id;
    private String  descr;

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

    @Override
    public String toString() {
        return "CarBodyType{" + "id=" + id
                + ", descr='" + descr + '\'' + '}';
    }
}
