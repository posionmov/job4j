package ru.job4j.tracker;
import java.util.*;

/**
 * Class for working with arra of items
 * @author Galanov Sergey
 * @since 29.07.2018
 * @version 1.5
 */
public class Tracker {

    /**
     * Private variables
     * Contains:
     *  - array of Items
     *  - current number of Items
     *  - variable for create random number
     */
    private Item[] items = new Item[100];
    private int position = 0;
    private static final Random RANDOM = new Random();

    /**
     * Func for generate random id for Item
     * @return unique id (String format)
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RANDOM.nextInt());
    }


    /**
     * Func for adding new Item in array
     * @param item - new item
     * @return - this item
     */
    public Item add(Item item) {
        item.setID(this.generateId());
        this.items[position++] = item;
        return item;
    }

    /**
     * Func for replace Item in array
     * @param id - id for finding Item
     * @param item - new Item for this id
     */
    public boolean replace(String id, Item item) {
        boolean isCorrect = false;
        for (int i = 0; i <= position; i++) {
            if (items[i] != null && items[i].getId().equals(id)) {
                items[i] = item;
                items[i].setID(id); //Добавление в новый объект текущий id
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    /**
     * Func for delete Item if Array
     * @param id - id of deleting item
     * @return true if item is delete
     */
    public boolean delete(String id) {
        boolean isCorrect = false;
        for (int i = 0; i < position; i++) {
            if (this.items[i] != null && this.items[i].getId().equals(id)) {
                System.arraycopy(this.items, i + 1, this.items, i, this.items.length - 1 - i);
                this.position--;
                isCorrect = true;
                break;
            }
        }
        return isCorrect;
    }

    /**
     * Func for recive all none-null Items if array
     * @return array of all none-null Items
     */
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

    /**
     * Func for finding all Items in array with one name
     * @param key - name (property)
     * @return array of Items with this name
     */
    public Item[] findByName(String key) {
        int matches = 0;
        Item[] result = new Item[this.position];
        for (int i = 0; i != this.position; i++) {
            if (this.items[i].getName().equals(key)) {
                result[matches++] = items[i];
            }
        }
        return Arrays.copyOf(result, matches);
    }

    /**
     * Func for finding Item with unique id
     * @param id - unique id
     * @return Item with this unique id
     */
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
