package ru.job4j.tracker;

/**
 * Class for placing, editing and deleting bids
 * @author Galanov Sergey
 * @since 28.07.2018
 * @version 1.0
 */
public class StartUI {

    /**
     * Contains private variable of menu
     */
    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FINDID = 4;
    private static final int FINDNAME = 5;
    private static final int EXIT = 6;

    /**
     * Contains private variable:
     * exit = mark for exit program
     * input - final object of class ConsoleInput
     * tracker - final object of class Tracker
     */
    private boolean exit = false;
    private final ConsoleInput input;
    private final Tracker tracker;

    /**
     * Constructor for these class
     * @param input - initializing for class ConsoleInput
     * @param tracker - initializing for class Tracker
     */
    public StartUI(ConsoleInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Func for show and read elements of menu
     */
    public void init() {
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите номер интересующей Вас операции: ");
            if (answer.equals(Integer.toString(ADD))) {
                this.addItem();
            } else if (answer.equals(Integer.toString(SHOW))) {
                this.showAllItems();
            } else if (answer.equals(Integer.toString(EDIT))) {
                this.editItem();
            } else if (answer.equals(Integer.toString(DELETE))) {
                this.deleteItem();
            } else if (answer.equals(Integer.toString(FINDID))) {
                this.findItemById();
            } else if (answer.equals(Integer.toString(FINDNAME))) {
                this.findByName();
            } else if (answer.equals(Integer.toString(EXIT))) {
                System.out.println("Выключение");
                this.exit = true;
            }
        }
    }

    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }

    /**
     * Func for show menu
     */
    private void showMenu() {
        System.out.println("0. Добавить заявку");
        System.out.println("1. Показать все заявки");
        System.out.println("2. Редактировать заявку");
        System.out.println("3. Удалиь заявку");
        System.out.println("4. Найти заявку по id");
        System.out.println("5. Найти заявки по имени");
        System.out.println("6. Выйти из программы");
    }

    /**
     * Func for adding items
     */
    private void addItem() {
        System.out.println("<----------------- Создание новой заявки ----------------->");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        long date = System.nanoTime();
        Item item = new Item(name, desc, date);
        tracker.add(item);
        System.out.println("<--------Заявка c id:" + item.getId() + " создана!-------->");
    }

    /**
     * Func for show all items
     */
    private void showAllItems() {
        Item[] items = this.tracker.findAll();
        System.out.println("<-------------- Список всех текущих заявок -------------->");
        for (Item item : items) {
            System.out.println("Id: " + item.getId() + ", Имя: " + item.getName() + ", Описание: " + item.getDescription());
        }
        System.out.println("<--------- Окончание списока всех текущих заявок --------->");
    }

    /**
     * Func for validation of input data
     * @param type - type of operation ("id" or "name")
     * @return correct data
     */
    private String correctData(String type) {
        boolean correct = false;
        String result = null;
        if (type.equals("id")) {
            result = this.input.ask("Введите id: ");
            while (!correct) {
                if (this.tracker.findById(result) == null) {
                    result = this.input.ask("Не верный id, попробуйте еще раз: ");
                } else {
                    correct = true;
                }
            }
        } else if (type.equals("name")) {
            result = this.input.ask("Введите имя: ");
            while (!correct) {
                if (this.tracker.findByName(result) == null || this.tracker.findByName(result).length < 1) {
                    result = this.input.ask("Не верное имя, попробуйте еще раз: ");
                } else {
                    correct = true;
                }
            }
        }
        return result;
    }

    /**
     * Func for edit some element in array
     */
    private void editItem() {
        String id = this.correctData("id");
        Item item = this.tracker.findById(id);
        String chose = this.input.ask("Что вы хотите изменить? \n1 - Имя \n2 - Описание\n3 - Оба параметра\nВаш выбор: ");
        if (chose.equals("1")) {
            String newName = this.input.ask("Введите новое имя: ");
            item.setName(newName);
        } else if (chose.equals("2")) {
            String newDesc = this.input.ask("Введите новое описание: ");
            item.setDescription(newDesc);
        } else if (chose.equals("3")) {
            String newName = this.input.ask("Введите новое имя: ");
            item.setName(newName);
            String newDesc = this.input.ask("Введите новое описание: ");
            item.setDescription(newDesc);
        }
        System.out.println("<-------------- Заменяю содержимое заявки -------------->");
        this.tracker.replace(id, item);
        System.out.println("<----------------------- Готово! ----------------------->");
    }

    /**
     * Func for delete element in array
     */
    private void deleteItem() {
        String id = this.correctData("id");
        System.out.println("<-------------- Удаляю данную заявку -------------->");
        this.tracker.delete(id);
        System.out.println("<---------------- Заявка удалена! ----------------->");
    }

    /**
     * Func for finding item in array by id
     */
    private void findItemById() {
        String id = this.correctData("id");
        Item item = this.tracker.findById(id);
        System.out.println("<-------------- Информация о заявке --------------->");
        System.out.println("ID: " + item.getId());
        System.out.println("Имя: " + item.getName());
        System.out.println("Описание: " + item.getDescription());
        System.out.println("Дата создания: " + item.getCreated());
        System.out.println("<-------------- Информация о заявке --------------->");
    }

    /**
     * Func for finding item in array by id
     */
    private void findByName() {
        String name = this.correctData("name");
        Item[] items = this.tracker.findByName(name);
        System.out.println("Найденные заявки:");
        for (int i = 0; i != items.length; i++) {
            System.out.println("<------------- Информация о заявке № " + (i + 1) + "------------->");
            System.out.println("ID: " + items[i].getId());
            System.out.println("Имя: " + items[i].getName());
            System.out.println("Описание: " + items[i].getDescription());
            System.out.println("Дата создания: " + items[i].getCreated());
            System.out.println("<-------------- Информация о заявке --------------->");
        }
        System.out.println("<--------------- Конец найденных заявок --------------->");
    }
}

