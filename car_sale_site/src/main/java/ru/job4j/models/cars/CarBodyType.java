package ru.job4j.models.cars;

/**
 * Класс-модель кузова автомобиля
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class CarBodyType {

    /**
     * Поля класса:
     *      id
     *      описание (имя)
     */
    private int id;
    private String  descr;

    /**
     * Дефлоный конструктор класса
     */
    public CarBodyType() {

    }

    /**
     * Конструктор класса с полем id
     * @param id
     */
    public CarBodyType(int id) {
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
