package ru.job4j.search;

/**
 * Класс, описывающий поля для каждой записи человека в телефонный справочник
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class Person {

    /**
     * Содержит стандарные поля каждой записи
     */
    private String name; // Имя человека
    private String surname; // Фамилия человека
    private String phone; // Телефон человека
    private String address; // Адрес человека

    /**
     * Конструктор данного класса
     * Записывает передаваемые значения в поля данного класса
     * @param name - имя
     * @param surname - фамилия
     * @param phone - телефон
     * @param address - адрес
     */
    public Person(String name, String surname, String phone, String address) {
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.address = address;
    }

    /**
     * Функция гетер, возращает имя человека
     * @return имя человека
     */
    public String getName() {
        return this.name;
    }

    /**
     * Функция гетер, озращает фамилию человека
     * @return фамилия человека
     */
    public String getSurname() {
        return this.surname;
    }

    /**
     * Функция гетер, возращает телефон человека
     * @return телефон человека
     */
    public String getPhone() {
        return this.phone;
    }

    /**
     * Функция гетер, возращает адрес человека
     * @return фдрес человека
     */
    public String getAddress() {
        return this.address;
    }

}
