package ru.job4j.search;

import java.util.LinkedList;

/**
 * Класс-обертка над LinkedList
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.2
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
        int index = this.tasks.size();
        for (int i = 0; i != this.tasks.size(); i++) {
            if (this.tasks.get(i).getPrior() > task.getPrior()) {
                index = i;
                break;
            }
        }
        this.tasks.add(index, task);
    }

    /**
     * Возращает первый элемент из коллекции
     * @return
     */
    public Task take() {
        return this.tasks.poll();
    }
}
