package ru.job4j.models;

import javax.persistence.*;

/**
 * Класс-модель автомобиля
 * @author Galanov Sergey
 * @since 23.10.2018
 * @version 1.0
 */
@Entity
@Table(name = "cars")
public class Car {

    /**
     * Приватные поля класса
     * Содержат информацию:
     *      id машины
     *      описание машины
     *      обьект класса Body
     *      обьект класса Engine
     *      обьект класса Transmission
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "body_id")
    private Body body;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "engine_id")
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    /**
     * Дефолтный конструктор класса
     */
    public Car() {

    }

    /**
     * КОнструктор класса с полем id
     * @param id
     */
    public Car(int id) {
        this.id = id;
    }


    /**
     * Блок геттеров и сетеров
     */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Переопределенный метод, переводящий обьект в строку
     * @return строка, содержащая в себе все поля данного обьекта
     */
    @Override
    public String toString() {
        return "Car{" + "id=" + id
                + ", description='" + description + '\''
                + ", body=" + body
                + ", engine=" + engine
                + ", transmission=" + transmission + '}';
    }
}
