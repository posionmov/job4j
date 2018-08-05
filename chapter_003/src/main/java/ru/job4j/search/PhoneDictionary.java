package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для добавления и поискав коллекции
 * @author Galanov Sergey
 * @since 05.08.2018
 * @version 1.0
 */
public class PhoneDictionary {

    /**
     * Приватное поле, содержит коллекцию людей
     */
    private List<Person> persons = new ArrayList<Person>();

    /**
     * Функция по добавению людей в массив
     * @param person обьект класса Person со всеми его поляи (т.е. по конструктору)
     */
    public void addPersonInDictionary(Person person) {
        this.persons.add(person);
    }

    /**
     * Функция по поиску в коллекции обьекта класса Person по ключу
     * Производит поиск по каждому полю каждого обьекта в коллекции
     * @param key - ключ, по которому производится поиск
     * @return новая коллекция, которая содержит все обьекы класса Person, в полях которых есть ключ
     */
    public List<Person> findPersons(String key) {
        List<Person> result = new ArrayList<Person>();
        for (Person person : this.persons) {
            if ((person.getName().contains(key))
                    || (person.getSurname().contains(key))
                    || (person.getPhone().contains(key))
                    || (person.getAddress().contains(key))) {
                result.add(person);
            }
        }
        System.out.println(result.size());
        return result;
    }
}
