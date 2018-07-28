package ru.job4j.tracker;

public class StartUI {
    private static final int ADD = 0;
    private static final int SHOW = 1;
    private static final int EDIT = 2;
    private static final int DELETE = 3;
    private static final int FINDID = 4;
    private static final int FINDNAME = 5;
    private static final int EXIT = 6;

    private boolean exit = false;

    private final ConsoleInput input;
    private final Tracker tracker;

    public StartUI(ConsoleInput input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

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

    private void showMenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Please select\n");
    }

    private void addItem() {
        System.out.println("<----------------- Создание новой заявки ----------------->");
        String name = this.input.ask("Введите имя заявки: ");
        String desc = this.input.ask("Введите описание заявки: ");
        long date = System.nanoTime();
        Item item = new Item(name, desc, date);
        tracker.add(item);
        System.out.println("<--------Заявка c id:" + item.getId() + " создана!-------->");
    }

    private void showAllItems() {
        Item[] items = this.tracker.findAll();
        System.out.println("<-------------- Список всех текущих заявок -------------->");
        for (Item item : items) {
            System.out.println("Id: " + item.getId() + ", Имя: " + item.getName() + ", Описание: " + item.getDescription());
        }
        System.out.println("<--------- Окончание списока всех текущих заявок --------->");
    }

    private String correctData(String type) {
        boolean correct = false;
        String result = null;
        if (type.equals("id")) {
            result = this.input.ask("Введите id: ");
            while (!correct) {
                if (tracker.findById(result) == null) {
                    result = this.input.ask("Не верный id, попробуйте еще раз: ");
                } else {
                    correct = true;
                }
            }
        } else if (type.equals("name")) {
            result = this.input.ask("Введите имя: ");
            while (!correct) {
                if (tracker.findByName(result) == null) {
                    result = this.input.ask("Не верное имя, попробуйте еще раз: ");
                } else {
                    correct = true;
                }
            }
        }
        return result;
    }

    private void editItem() {
        String id = this.correctData("id");
        Item item = this.tracker.findById(id);
        String chose = this.input.ask("Что вы хотите изменить? \n1 - Имя \n2 - Описание\n3 - Оба параметра");
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

    private void deleteItem() {
        String id = this.correctData("id");
        System.out.println("<-------------- Удаляю данную заявку -------------->");
        this.tracker.delete(id);
        System.out.println("<---------------- Заявка удалена! ----------------->");
    }

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

