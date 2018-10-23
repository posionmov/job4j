package ru.job4j.models.cars;

import java.awt.*;

public class Car {
    private int id;                         // id автомобиля в бд
    private String descr;                   // название
    private CarBodyType bodyType;           // тип кузова
    private CarMark mark;                   // марка автомобиля
    private CarModel model;                 // модель
    private int yearOfManufactured;         // год выпуска
    private int mileage;                    // пробег
    private CarTransmission transmission;   // коробка передач
    private CarEngine engine;               // тип двигателя (бензин, дизель и тп)
    private float engineCapacity;           // обьем двигателя
    private float power;                    // мощность двигателя
    private CarDrive drive;                 // привод автомобиля (передний, задний, полный)
    private boolean leftRudder;             // леворульный (да, нет)
    private CarColor carColor;              // цвет машины


}
