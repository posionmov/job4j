package ru.job4j.notify;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс для хранения в частично блокирующей хэш мапе
 * @author Galanov Sergey
 * @since 24.08.2018
 * @version 1.0
 */
public class CashBase {

    /**
     * Внутренние поля класса
     * Содержат обьект класса ConcurrentHashMap
     * В качестве ключа выступает обьект класса Integer (целое число)
     * В качестве значения выступает обьект класса Base
     */
    private ConcurrentHashMap<Integer, Base> map = new ConcurrentHashMap<>();

    /**
     * Метод добавления в мапу данных
     * @param model - обьект класса Base, который необходимо добавить в мапу
     */
    public void add(Base model) {
        this.map.put(model.id, model);
    }

    /**
     * Метод обновления обьекта в мапе
     * Если у мапы и обновляемого обьекта разные версии, то выкидывает исключение
     * Если одинаковые то делает инкремент у версии
     * @param model - обьект класса Base, который необходимо обновить
     */
    public void update(Base model) {
        if (!this.checkBase(model)) {
            throw new OptimisticException("Обьект не актуальный");
        }
        model.version++;
        this.map.replace(model.id, model);
    }

    /**
     *
     * Метод удаления обьектов из мапы
     * @param model - обьект класса Base
     */
    public void delete(Base model) {
        this.map.remove(model.id);
    }

    /**
     * Вспомогательный метод получения обьекта из мапы по ключу (id)
     * @param id - ключ для поиска в мапе
     * @return актуальный обьект класса Base шы мапы
     */
    public Base findById(int id) {
        return this.map.get(id);
    }

    /**
     * Вспомогательный метод, проверяющий версию изменяемого обьекта
     * @param model - изменяемый объект
     * @return true если обьект актуальный
     */
    private boolean checkBase(Base model) {
        int versionInMap = this.findById(model.id).version;
        return (model.version == versionInMap);
    }

}

class Base {
    int id;
    int version;
    String name;

    public Base(int id, int version, String name) {
        this.id = id;
        this.version = version;
        this.name = name;
    }
}
