package ru.job4j.tracker;
import java.util.*;

public class Tracker {

    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RANDOM = new Random();

    //Генерация id
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }


    //Добавление заявки
    public Item add(Item item) {
        item.setID(String.valueOf(generateId()));
        this.items[position++] = item;
        return item;
    }

    //Редактирование заявки
    public void replace(String id, Item item) {
        for (int i = 0; i <= position; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                items[i] = item;
            }
        }
    }

    //Удаление заявки
    public void delete(String id) {
        for (int i = 0; i <= this.position; i++) {
            if (this.items[i].getId().equals(id)) {
                for (int j = i; j <= this.position; j++) {
                    if (j < this.position) {
                        this.items[j] = this.items[j + 1];
                    }
                }
                break;
            }
        }
        this.items = Arrays.copyOf(this.items, --position);
    }

    //Получение списка всех заявок
    public Item[] findAll() {
        Item[] result = new Item[position];
        int index = 0;
        for (int i = 0; i != position; i++) {
            if (items[i] != null) {
                result[index++] = items[i];
            }
        }
        return result;
    }

    //Получение списка по имени
    public Item[] findByName(String key) {
        Item[] result = new Item[position];
        int quantity = position;
        int index = 0;
        for (int i = 0; i != position; i++) {
            if (items[i].genName().equals(key)) {
                result[index++] = items[i];
                quantity--;
            }
        }
        return result;
    }

    //Получние заявки по id
    public Item findById(String id) {
        Item result = null;
        for (Item item : this.items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
            }
        }
        return result;
    }
}
