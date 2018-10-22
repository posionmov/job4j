package ru.job4j.store;

import ru.job4j.todolist.ListModel;

import java.util.List;

/**
 * Интерфейс для работы с БД
 * @author Galanov Sergey
 * @since 22.10.2018
 * @version 1.0
 */
public interface Store {
    List<ListModel> getAllObject();
    void addNewObject(ListModel model);
    void updateObject(ListModel model);
}
