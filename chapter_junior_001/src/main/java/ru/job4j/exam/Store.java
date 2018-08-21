package ru.job4j.exam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс для сравнения двух коллекций юзеров
 * @author Galanov Sergey
 * @since 21.08.2018
 * @version 1.0
 */
public class Store {

    /**
     * Содержит приватные поля
     * В данный момент тут записана ссылка на карту, содержащую в качестве ключа - Строку,
     * а в качестве значения - Целочисленную переменную
     */
    private Map<String, Integer> result;

    /**
     * Метод, сравнивающий две коллекции
     * - Сперва проходит по коллекциям и ищет схожие по id элементы. Все эти элементы записываются
     * в переменную same.
     * - Все переменные, у которых не совпадает поле name увеличивают величину переменной
     * changes (которая символизирует количество измененных элементов)
     * - Количество добавленных элементов подсчитывается путем вычитания из количества элементов
     * второй коллекции количество same элементов
     * - Количество удаленных элементов считается путем вычитания из длины первой коллекции
     * количества same элементов
     *
     * @param previous - первая коллекция
     * @param current - вторая коллекция
     * @return - результат в виде Map
     */
    public Map<String, Integer> diff(List<User> previous, List<User> current) {
        this.result = new HashMap<>();
        int same = 0;
        int changes = 0;
        for (int i = 0; i < previous.size(); i++) {
            for (int j = 0; j < current.size(); j++) {
                if (previous.get(i).getID() == current.get(j).getID()) {
                    same++;
                    if (!previous.get(i).getName().equals(current.get(j).getName())) {
                        changes++;
                    }
                }
            }
        }
        this.result.put("Изменено", changes);
        if (previous.size() > same) {
            this.result.put("Удалено", previous.size() - same);
        } else {
            this.result.put("Удалено", 0);
        }
        this.result.put("Добавлено", current.size() - same);
        return this.result;
    }
}

/**
 * Класс, который будет использоваться в сравниваемых коллекциях
 */
class User {

    /**
     * Содержит приватные поля:
     * - id (int)
     * - имя (String)
     */
    private int id;
    private String name;

    /**
     * Конструктор класса
     * @param id нового обьекта
     * @param name имя нового обьекта
     */
    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Геттер поля id
     * @return id
     */
    public int getID() {
        return this.id;
    }

    /**
     * Геттер поля name
     * @return name
     */
    public String getName() {
        return this.name;
    }
}
