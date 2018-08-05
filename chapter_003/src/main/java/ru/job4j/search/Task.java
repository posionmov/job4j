package ru.job4j.search;

/**
 * Класс, описывающий задачу и ее приоритетность
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class Task {

    /**
     * Содержит поля класса
     */
    private String description; // Описание задачи
    private int prior; // Ее приоритетность

    /**
     * Конструктор класса
     * @param desc описание задачи
     * @param prior приоритетность задачи
     */
    public Task(String desc, int prior) {
        this.description = desc;
        this.prior = prior;
    }

    /**
     * Функция, которая возращает описание текущего обьекта класса
     * @return описание обьекта
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Функция, которая возращает приоритетность данной задачи
     * @return приоритет
     */
    public int getPrior() {
        return this.prior;
    }

}
