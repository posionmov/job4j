package ru.job4j.generics;

import java.util.Iterator;

/**
 * Абстрактный класс, описывающий общую работу с оберткой массива SimpleArray
 * @author Galanov Sergey
 * @since 14.08.2018
 * @version 1.2
 * @param <T> - любой ссылочный тип данных, наследуемый от класса Base
 */
public abstract class AbstractStore<T extends Base> implements Store<T> {

    /**
     * Содержит внутренее поле.
     * По сути это просто экземпляр класса SimpleArray
     */
    private SimpleArray<T> array = new SimpleArray<T>(10);

    /**
     * Метод добавления элемента в массив
     * Вызывает у экземпляра класса SimpleArray метод add, который добавляет в конец новое значение
     * @param model обьект класса Т (те любого класса, который наследуется от класса Base)
     */
    public void add(T model) {
        array.add(model);
    }

    /**
     * Метод изменения обьекта в массиве
     * @param id - String значение, которое присваиваетя каждому обьекту класса Base. По нему и производим поиск.
     * @param model - обьект класса Т, который следует поместить вместо найденного элемента
     * @return true если элемент найден и заменен
     */
    public boolean replace(String id, T model) {
        boolean result = false;
        for (int i = 0; i < this.array.getSize(); i++) {
            if (this.array.get(i) != null && this.array.get(i).getId().equals(id)) {
                this.array.set(i, model);
                result = true;
                break;
            }
        }
        return result;
    }

    /**
     * Метод, который удаляет элемент из массива по id
     * @param id идентификатор обьекта
     * @return true если элемент удален
     */
    public boolean delete(String id) {
        return this.array.delete(this.findById(id));
    }

    /**
     * Метод поиска элемента в массиве
     * @param id идентификатор обьекта в массиве
     * @return найденный обьект
     */
    public T findById(String id) {
        T result = null;
        for (T user : this.array) {
            if (user.getId().equals(id)) {
                result = user;
                break;
            }
        }
        return result;
    }

}

