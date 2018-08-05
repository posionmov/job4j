package ru.job4j.search;

import java.util.LinkedList;

/**
 * Класс-обертка над LinkedList
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class PriorityQuene {

    /**
     * Содержит поля класса
     */
    private LinkedList<Task> tasks = new LinkedList<>(); // Коллекция заданий
    private int items = 0; // Текущее количество элементов в коллекции

    /**
     * Функция, которая добавляет элемент в коллекцию, а затем упорядочивает коллекцию
     * @param task добавляемое задание
     */
    public void putInTasks(Task task) {
        this.tasks.add(task);
        this.items++;
        for (int i = 0; i != this.items - 1; i++) {
            if (this.tasks.get(i).getPrior() > task.getPrior()) {
                this.tasks.set(i + 1, this.tasks.get(i));
                this.tasks.set(i, task);
            }
        }
    }

    /**
     * Возращает первый элемент из коллекции
     * @return
     */
    public Task take() {
        return this.tasks.poll();
    }
}
