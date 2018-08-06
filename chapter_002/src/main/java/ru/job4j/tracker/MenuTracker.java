package ru.job4j.tracker;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для работы с меню
 * @author Galanov Sergey
 * @since 06.08.2018
 * @version 1.3
 */
public class MenuTracker {

    /**
     * Содержит приватные переменные
     */
    private Input input; //Используемый ввод
    private Tracker tracker; //Используемый массив
    private List<UserAction> actions = new ArrayList<UserAction>(); // Коллекция с действиями
    private int curKey = 0;

    /**
     * Конструктор для данного класса
     * @param input объект, который использует интерфейс Input
     * @param tracker объект класса Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Функция для добавления в массив всех элементов меню (которые являются отдельными внутренними классами)
     */
    public List<Integer> fillActions(StartUI ui) {
        List<Integer> result;
        this.actions.add(this.new AddItem(curKey++, "Создание заявки"));
        this.actions.add(this.new ShowAll(curKey++, "Показать все заявки"));
        this.actions.add(new MenuTracker.EditItem(curKey++, "Редактирование заявки"));
        this.actions.add(new MenuTracker.DeleteItem(curKey++, "Удаление заявки"));
        this.actions.add(new FindItemById(curKey++, "Поиск заявки по id"));
        this.actions.add(new FindByName(curKey++, "Поиск заявки по имени"));
        this.actions.add(new ExitProgram(curKey++, "Выйти из программы", ui));
        result = this.appendInArray();
        return result;
    }

    /**
     * Функция по добавлению в массив элементов меню
     * @return массив, который содержит все элементы меню
     */
    private List<Integer> appendInArray() {
        List<Integer> result = new ArrayList<Integer>();
        for (UserAction userAction : this.actions) {
            result.add(userAction.key());
        }
        return result;
    }

    /**
     * Функция для вывода в консоль всех пункторв меню (и еще дополнительного пункта, отвечающего за выход из программы)
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("----------------------------------------------------------");
    }

    /**
     * Функция для выполнения выбранного действия
     * @param key - ключ конкретного действия
     */
    public int select(int key) {

       if (key < this.actions.size()) {
           this.actions.get(key).execute(this.input, this.tracker);
           return key;
       } else {
           return 6;
       }
    }

    //<--------------- Не статические классы ----------------->

    /**
     * Класс для добавления элемента в массив
     */
    private class AddItem extends BaseAction {

        /**
         * Конструктор данного класса
         * @param key - ключ
         * @param name - имя операции
         */
        public AddItem(int key, String name) {
            super(key, name);
        }

        /**
         * Метод интерфейса для добавления элемента в массив
         * @param input объект, который использует интерфейс Input
         * @param tracker объект класса Tracker
         */
        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("<----------------- Создание новой заявки ----------------->");
            String name = input.ask("Введите имя заявки: ");
            String desc = input.ask("Введите описание заявки: ");
            long date = System.nanoTime();
            Item item = new Item(name, desc, date);
            tracker.add(item);
            System.out.println("<--------Заявка c id:" + item.getId() + " создана!-------->");
        }

        /**
         * Метод для отображения информации о данном действии в меню
         * @return - информация о данном действии
         */
        public String info() {
            return super.info();
        }
    }

    /**
     * Класс для нахождения и отображения в консоли всех элементов массив
     */
    private class ShowAll extends BaseAction {

        public ShowAll(int key, String name) {
            super(key, name);
        }

        /**
         * Данная функция находит все элементы в массиве и отображает в консоли всю инфрмацию о них
         * @param input объект, который использует интерфейс Input
         * @param tracker объект класса Tracker
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("<-------------- Список всех текущих заявок -------------->");
            for (Item item : tracker.findAll()) {
                System.out.println("Id: " + item.getId() + ", Имя: " + item.getName() + ", Описание: " + item.getDescription());
            }
            System.out.println("<--------- Окончание списока всех текущих заявок --------->");
        }

        /**
         * Метод для отображения информации о данном действии в меню
         * @return - информация о данном действии
         */
        public String info() {
            return super.info();
        }
    }

    //<-------------Static Classes----------------->

    /**
     * Класс для редактирования элементов в массиве
     */
    private static class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
        }

        /**
         * Функция для редактировнаия конкретного элемента в массве
         * @param input объект, который использует интерфейс Input
         * @param tracker объект класса Tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id: ");
            if (!id.equals("")) {
                String newName = input.ask("Введите новое имя: ");
                String newDesc = input.ask("Введите новое описание: ");
                Item item = new Item(newName, newDesc, System.nanoTime());
                boolean isEdited = tracker.replace(id, item);
                if (isEdited) {
                    System.out.println("<----------------------- Обьект поменялся ----------------------->");
                } else if (!isEdited) {
                    System.out.println("Запись не найдена, пожалуйста повторите.");
                    this.execute(input, tracker);
                }
            }
        }

        /**
         * Метод для отображения информации о данном действии в меню
         * @return - информация о данном действии
         */
        public String info() {
            return super.info();
        }
    }

    /**
     * Класс для удаления обьекта в массиве
     */
    private static class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
        }

        /**
         * Функция для удаления конкретного обьекта (по конкретному id) из массива
         * @param input объект, который использует интерфейс Input
         * @param tracker объект класса Tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Введите id: ");
            boolean isDelete =  tracker.delete(id);
            if (isDelete) {
                System.out.println("<---------------- Заявка удалена ----------------->");
            } else if (!id.equals("")) {
                System.out.println("Не найдена запись, пожалуйста повторите.");
                this.execute(input, tracker);
            }
        }

        /**
         * Метод для отображения информации о данном действии в меню
         * @return - информация о данном действии
         */
        public String info() {
            return super.info();
        }
    }
}

/**
 * Класс для поиска обьекта в массиве
 */
class FindItemById extends BaseAction {

    public FindItemById(int key, String name) {
        super(key, name);
    }

    /**
     * Функция для поиска обьекта в массиве по id и отображения в консоли
     * @param input объект, который использует интерфейс Input
     * @param tracker объект класса Tracker
     */
    public void execute(Input input, Tracker tracker) {
        String id = input.ask("Введите id: ");
        Item item = tracker.findById(id);
        if (item != null) {
            System.out.println("<-------------- Информация о заявке -------------->");
            System.out.println("ID: " + item.getId());
            System.out.println("Имя: " + item.getName());
            System.out.println("Описание: " + item.getDescription());
            System.out.println("Дата создания: " + item.getCreated());
            System.out.println("<-------------- Информация о заявке --------------->");
        } else if (!id.equals("")) {
            System.out.println("Заявка не найдена, попробуйте  еще раз.");
            this.execute(input, tracker);
        }
    }

    /**
     * Метод для отображения информации о данном действии в меню
     * @return - информация о данном действии
     */
    public String info() {
        return super.info();
    }
}

/**
 * Класс для поиска обьектов в массиве
 */
class FindByName extends BaseAction {

    public FindByName(int key, String name) {
        super(key, name);
    }

    /**
     * Функция по поиску обьектов в массиве по имени и отображении в консоли
     * @param input объект, который использует интерфейс Input
     * @param tracker объект класса Tracker
     */
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя: ");
        List<Item> items = tracker.findByName(name);
        if (items.size() > 0) {
            System.out.println("Найденные заявки:");
            for (int i = 0; i != items.size(); i++) {
                System.out.println("<------------- Информация о заявке № " + (i + 1) + "----------->");
                System.out.println("ID: " + items.get(i).getId());
                System.out.println("Имя: " + items.get(i).getName());
                System.out.println("Описание: " + items.get(i).getDescription());
                System.out.println("Дата создания: " + items.get(i).getCreated());
                System.out.println("<-------------- Информация о заявке ------------->");
            }
            System.out.println("<--------------- Конец найденных заявок --------------->");
        } else {
            System.out.println("Заявки не найены");
        }
    }

    /**
     * Метод для отображения информации о данном действии в меню
     * @return - информация о данном действии
     */
    public String info() {
        return super.info();
    }
}

class ExitProgram extends BaseAction {

    private final StartUI ui;

    public ExitProgram(int key, String name, StartUI ui) {
        super(key, name);
        this.ui = ui;
    }

    public void execute(Input input, Tracker tracker) {
        this.ui.isOver();
    }

    public String info() {
        return super.info();
    }
}
