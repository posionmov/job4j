package ru.job4j.models.cars;

import java.util.List;

/**
 * Класс-модель марки автомобиля
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class CarMark {

    /**
     * Поля класса:
     *      id
     *      описание (имя)
     *      коллекция моделей машин, которые входят в данную марку
     */
    private int id;
    private String descr;
    private List<CarModel> models;

    /**
     * Дефлоный конструктор класса
     */
    public CarMark() {

    }

    /**
     * Конструктор класса с полем id
     * @param id
     */
    public CarMark(int id) {
        this.id = id;
    }

    /**
     * Блок геттеров и сеттеров
     */
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

    /**
     * Переопределенный метод toString()
     * @return строка, описывающая всет екущие поля обьекта данного класса
     */
    @Override
    public String toString() {
        return "CarMark{" + "id=" + id
                + ", descr='" + descr + '\''
                + ", models=" + models + '}';
    }
}
