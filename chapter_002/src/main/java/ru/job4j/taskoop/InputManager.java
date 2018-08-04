package ru.job4j.taskoop;

/**
 * Класс работы с каждым обьеком класса, который описывает обьект класса кофе
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class InputManager {

    /**
     * Содержит поля данного класса:
     */
    private InputInterface input;
    private BaseCoffeItem[] coffes = new BaseCoffeItem[100];
    private int curPosition = 0;
    public CofeMachine machine;
    private int keys = 1;

    /**
     * Конструктор класса
     * @param input
     */
    public InputManager(InputInterface input, CofeMachine machine) {
        this.input = input;
        this.machine = machine;
    }

    /**
     * Возращает длину массива действий
     * @return
     */
    public int getKeys() {
        return this.keys;
    }

    /**
     * Метод заполняет массив действий всеми кофе и методом "выход"
     */
    public void getAllCoffes() {
        this.coffes[this.curPosition++] = new BaseCoffeItem("Капучино", 20, keys++);
        this.coffes[this.curPosition++] = new BaseCoffeItem("Лотте", 18, keys++);
        this.coffes[this.curPosition++] = new BaseCoffeItem("Экспрессо", 17, keys++);
        this.coffes[this.curPosition++] = new BaseCoffeItem("Американо", 23, keys++);
        this.coffes[this.curPosition++] = this.new EndWork(this.machine, "Выйти", keys++);
    }

    /**
     * Метод показывает меню пользователю
     */
    public void showMenu() {
        System.out.println("Доступные кофе в данный момент: ");
        for (BaseCoffeItem item : coffes) {
            if (item != null) {
                System.out.println(String.format("%s - %s, цена - %s", item.getKey(), item.getName(), item.getPrice()));
            }
        }
    }

    /**
     * метод выполнения работы
     * @param key - ключ элемента
     */
    public void doWork(int key) {
        if (key != this.keys - 1) {
            System.out.println(String.format("Цена данного напитка - %s", this.coffes[key - 1].getPrice()));
            int moneyIn = this.input.userChoise("Какую купюру вы вставите?", 5000);
            int[] result = new Change().change(moneyIn, this.coffes[key - 1].getPrice());
            if (result.length > 0) {
                System.out.println("Вам выдадутся следующие монеты:");
                for (int i = 0; i != result.length; i++) {
                    System.out.println(result[i]);
                }
            }
        } else {
            System.out.println("Завершаю работу");
            this.machine.stopWork();
        }
    }

    private class EndWork extends BaseCoffeItem {

        private CofeMachine machine;

        protected EndWork(CofeMachine machine, String name, int key) {
            super(name, 0, key);
            this.machine = machine;
        }
    }
}
