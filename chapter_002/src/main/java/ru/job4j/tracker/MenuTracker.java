package ru.job4j.tracker;

/**
 * Класс для работы с меню
 * @author Galanov Sergey
 * @since 01.08.2018
 * @version 1.1
 */
public class MenuTracker {

    /**
     * Содержит приватные переменные
     */
    private Input input; //Используемый ввод
    private Tracker tracker; //Используемый массив
    private UserAction[] actions = new UserAction[7]; //Массив с действиями

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
    public int[] fillActions() {
        int[] result = new int[7];
        this.actions[0] = this.new AddItem();
        this.actions[1] = this.new ShowAll();
        this.actions[2] = new MenuTracker.EditItem();
        this.actions[3] = new MenuTracker.DeleteItem();
        this.actions[4] = new FindItemById();
        this.actions[5] = new FindByName();
        this.actions[6] = new ExitProgram();
        result = this.appendInArray();
        return result;
    }

    /**
     * Функция по добавлению в массив элементов меню
     * @return массив, который содержит все элементы меню
     */
    private int[] appendInArray() {
        int[] result = new int[this.actions.length];
        for (int i = 0; i < result.length; i++) {
            result[i] = this.actions[i].key();
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

       if (key < this.actions.length) {
           this.actions[key].execute(this.input, this.tracker);
           return key;
       } else {
           return 6;
       }
    }

    //<--------------- Не статические классы ----------------->

    /**
     * Класс для добавления элемента в массив
     */
    private class AddItem implements UserAction {

        /**
         * Метод интерфейса
         * @return ключ данной операции
         */
        public int key() {
            return 0;
        }

        /**
         * Метод интерфейса для добавления элемента в массив
         * @param input объект, который использует интерфейс Input
         * @param tracker объект класса Tracker
         */
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
            return String.format("%s - Создание заявки", this.key());
        }
    }

    /**
     * Класс для нахождения и отображения в консоли всех элементов массив
     */
    private class ShowAll implements UserAction {

        /**
         * Метод интерфейса
         * @return ключ данной операции
         */
        public int key() {
            return 1;
        }

        /**
         * Данная функция находит все элементы в массиве и отображает в консоли всю инфрмацию о них
         * @param input объект, который использует интерфейс Input
         * @param tracker объект класса Tracker
         */
        public void execute(Input input, Tracker tracker) {
            Item[] items = tracker.findAll();
            System.out.println("<-------------- Список всех текущих заявок -------------->");
            for (Item item : items) {
                System.out.println("Id: " + item.getId() + ", Имя: " + item.getName() + ", Описание: " + item.getDescription());
            }
            System.out.println("<--------- Окончание списока всех текущих заявок --------->");
        }

        /**
         * Метод для отображения информации о данном действии в меню
         * @return - информация о данном действии
         */
        public String info() {
            return String.format("%s - Показать все заявки", this.key());
        }
    }

    //<-------------Static Classes----------------->

    /**
     * Класс для редактирования элементов в массиве
     */
    private static class EditItem implements UserAction {

        /**
         * Метод интерфейса
         * @return ключ данной операции
         */
        public int key() {
            return 2;
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
            return String.format("%s - Редактирование заявки", this.key());
        }
    }

    /**
     * Класс для удаления обьекта в массиве
     */
    private static class DeleteItem implements UserAction {

        /**
         * Метод интерфейса
         * @return ключ данной операции
         */
        public int key() {
            return 3;
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
            return String.format("%s - Удаление заявки", this.key());
        }
    }
}

/**
 * Класс для поиска обьекта в массиве
 */
class FindItemById implements UserAction {

    /**
     * Метод интерфейса
     * @return ключ данной операции
     */
    public int key() {
        return 4;
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
        return String.format("%s - Поиск заявки по id", this.key());
    }
}

/**
 * Класс для поиска обьектов в массиве
 */
class FindByName implements UserAction {
    /**
     * Метод интерфейса
     * @return ключ данной операции
     */
    public int key() {
        return 5;
    }

    /**
     * Функция по поиску обьектов в массиве по имени и отображении в консоли
     * @param input объект, который использует интерфейс Input
     * @param tracker объект класса Tracker
     */
    public void execute(Input input, Tracker tracker) {
        String name = input.ask("Введите имя: ");
        Item[] items = tracker.findByName(name);
        if (items.length > 0) {
            System.out.println("Найденные заявки:");
            for (int i = 0; i != items.length; i++) {
                System.out.println("<------------- Информация о заявке № " + (i + 1) + "----------->");
                System.out.println("ID: " + items[i].getId());
                System.out.println("Имя: " + items[i].getName());
                System.out.println("Описание: " + items[i].getDescription());
                System.out.println("Дата создания: " + items[i].getCreated());
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
        return String.format("%s - Поиск заявки по имени", this.key());
    }
}

class ExitProgram implements UserAction {

    public int key() {
        return 6;
    }

    public void execute(Input input, Tracker tracker) {

    }

    public String info() {
        return String.format("%s - Выйти из программы", this.key());
    }
}
