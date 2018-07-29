package ru.job4j.tracker;

/**
 * Class for placing, editing and deleting bids
 * @author Galanov Sergey
 * @since 29.07.2018
 * @version 1.4
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
                System.out.println("----------------------------------------------------------");
                this.addItem();
            } else if (answer.equals(Integer.toString(SHOW))) {
                System.out.println("----------------------------------------------------------");
                this.showAllItems();
            } else if (answer.equals(Integer.toString(EDIT))) {
                System.out.println("----------------------------------------------------------");
                this.editItem();
            } else if (answer.equals(Integer.toString(DELETE))) {
                System.out.println("----------------------------------------------------------");
                this.deleteItem();
            } else if (answer.equals(Integer.toString(FINDID))) {
                System.out.println("----------------------------------------------------------");
                this.findItemById();
            } else if (answer.equals(Integer.toString(FINDNAME))) {
                System.out.println("----------------------------------------------------------");
                this.findByName();
            } else if (answer.equals(Integer.toString(EXIT))) {
                System.out.println("----------------------------------------------------------");
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
        String result = "";
        if (type.equals("id")) {
            result = this.input.ask("Для выхода нажмите Enter." + System.lineSeparator() + "Введите id: ");
        } else if (type.equals("name")) {
            result = this.input.ask("Для выхода нажмите Enter." + System.lineSeparator() + "Введите имя: ");
        }
        return result;
    }

    /**
     * Func for edit some element in array
     */
    private void editItem() {
        String id = this.correctData("id");
        if (!id.equals("")) {
            String newName = this.input.ask("Введите новое имя: ");
            String newDesc = this.input.ask("Введите новое описание: ");
            Item item = new Item(newName, newDesc, System.nanoTime());
            boolean isEdited = this.tracker.replace(id, item);
            if (isEdited) {
                System.out.println("<----------------------- Готово! ----------------------->");
            } else if (!isEdited) {
                System.out.println("Запись не найдена, пожалуйста повторите.");
                this.editItem();
            }
        }
    }

    /**
     * Func for delete element in array
     */
    private void deleteItem() {
        String id = this.correctData("id");
        boolean isDelete =  this.tracker.delete(id);
        if (isDelete) {
            System.out.println("<---------------- Заявка удалена! ----------------->");
        } else if (!id.equals("")) {
            System.out.println("Не найдена запись, пожалуйста повторите.");
            this.deleteItem();
        }
    }

    /**
     * Func for finding item in array by id
     */
    private void findItemById() {
        String id = this.correctData("id");
        Item item = this.tracker.findById(id);
        if (item != null) {
            System.out.println("<-------------- Информация о заявке --------------->");
            System.out.println("ID: " + item.getId());
            System.out.println("Имя: " + item.getName());
            System.out.println("Описание: " + item.getDescription());
            System.out.println("Дата создания: " + item.getCreated());
            System.out.println("<-------------- Информация о заявке --------------->");
        } else if (!id.equals("")) {
            System.out.println("Заявка не найдена, попробуйте  еще раз.");
            this.findItemById();
        }
    }

    /**
     * Func for finding item in array by id
     */
    private void findByName() {
        String name = this.correctData("name");
        Item[] items = this.tracker.findByName(name);
        if (items.length > 0) {
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
        } else {
            System.out.println("Заявки не найены");
        }
    }
}

