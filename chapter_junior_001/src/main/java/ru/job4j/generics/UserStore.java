package ru.job4j.generics;

/**
 * Класс, описывающий работу с массивом, содержащим элементы класса User
 * @author Galanov Sergey
 * @since 13.08.2018
 * @version 1.0
 */
public class UserStore extends AbstractStore<User> {

    /**
     * Метод добавления нового элемента в массив
     * @param model обьект класса Т (те любого класса, который наследуется от класса Base)
     */
    public void add(User model) {
        super.add(model);
    }

    /**
     * Метод замены элемента в массиве
     * @param id - String значение, которое присваиваетя каждому обьекту класса Base. По нему и производим поиск.
     * @param model - обьект класса Т, который следует поместить вместо найденного элемента
     * @return
     */
    public boolean replace(String id, User model) {
        return super.replace(id, model);
    }

    /**
     * Метод удаления элемента из массива
     * @param id идентификатор обьекта
     * @return
     */
    public boolean delete(String id) {
        return super.delete(id);
    }

    /**
     * Метод поиска элемента в массиве
     * @param id идентификатор обьекта в массиве
     * @return
     */
    public User findById(String id) {
        return super.findById(id);
    }
}
