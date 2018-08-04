package ru.job4j.taskoop;

/**
 * Класс, который описывает стандартные поля каждого вида кофе
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class BaseCoffeItem {

    /**
     * Содержит стандартные поля кадого вида кофе: имя, цена и ключ
     */
    private String name;
    private int price;
    private int key;

    /**
     * Конструктор данного класса
     * @param name - имя
     * @param price - цена
     * @param key - ключ
     */
    protected BaseCoffeItem(String name, int price, int key) {
        this.name = name;
        this.price = price;
        this.key = key;
    }

    /**
     * Метод возращает имя обьекта класса
     * @return имя обьекта класса
     */
    public String getName() {
        return this.name;
    }

    /**
     * Возращает цену обьекта данного класса
     * @return цена обьекта
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Возращает ключ данного обьекта класса
     * @return ключ обьекта
     */
    public int getKey() {
        return this.key;
    }





}
