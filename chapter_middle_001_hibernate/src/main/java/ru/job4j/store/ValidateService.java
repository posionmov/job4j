package ru.job4j.store;

import ru.job4j.todolist.ListModel;

import java.util.List;

public enum  ValidateService implements Store {

    INSTANCE;

    @Override
    public List<ListModel> getAllObject() {
        return DbStore.INSTANCE.getAllObject();
    }

    @Override
    public void addNewObject(ListModel model) {
        DbStore.INSTANCE.addNewObject(model);
    }

    @Override
    public void updateObject(ListModel model) {
        DbStore.INSTANCE.updateObject(model);
    }
}
