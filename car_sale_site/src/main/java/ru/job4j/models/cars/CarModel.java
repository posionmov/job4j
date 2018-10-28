package ru.job4j.models.cars;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Класс-модель модели автомобиля
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class CarModel {

    /**
     * Поля класса:
     *      id
     *      описание (имя)
     *      марка автомобиля (благодаря аннотации Jackson игнорирует это поле)
     */
    @JsonIgnore
    private CarMark mark;

    private int id;
    private String  descr;

    /**
     * Дефлоный конструктор класса
     */
    public CarModel() {

    }

    /**
     * Конструктор класса с полем id
     * @param id
     */
    public CarModel(int id) {
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

    public CarMark getMark() {
        return mark;
    }

    public void setMark(CarMark mark) {
        this.mark = mark;
    }

    /**
     * Переопределенный метод toString()
     * @return строка, описывающая всет екущие поля обьекта данного класса
     */
    @Override
    public String toString() {
        return "CarBodyType{" + "id=" + id
                + ", descr='" + descr + '\'' + '}';
    }
}
