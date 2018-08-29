package ru.job4j.lists;

import net.jcip.annotations.ThreadSafe;

/**
 * Класс-обертка над импровизированным динамическим arrayList
 * Добавлена синхронизация обращения к данным
 * @author Galanov Sergey
 * @since 29.08.2018
 * @version 1.0
 * @param <E> - любой класс
 */
@ThreadSafe
public class SynchDynamicArrayList<E> {

    /**
     * Содержит приватную ссылк уна обьект класса DynamicArrayList
     */
    private DynamicArrayList<E> arrayList = new DynamicArrayList<>();

    /**
     * Синхронизированный метод добавления
     * @param object - обьект класса Е, который необходимо добавить
     */
    public void add(E object) {
        synchronized (this) {
            this.arrayList.add(object);
        }
    }

    /**
     * Синхронизированный метод получения размера массива
     * @return размер массива
     */
    public int getSize() {
        int result;
        synchronized (this) {
            result = this.arrayList.getSize();
        }
        return result;
    }

    /**
     * Синхронизированный метод удаления обьекта из массива по индексу
     * @param index индекс элемента, которого необходимо удалить
     */
    public void delete(int index) {
        synchronized (this) {
            this.arrayList.delete(index);
        }
    }

    /**
     * Синхронизированный метод получения значения элемента из массива по индексу
     * @param index - индекс элемента, значение которого необходимо вернуть
     * @return значение элемента из массива по индексу
     */
    public E get(int index) {
        E result;
        synchronized (this) {
            result = this.arrayList.get(index);
        }
        return result;
    }

}
