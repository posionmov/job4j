package ru.job4j.taskoop;

public class InputManager {

    private Input input;
    private BaseCoffeItem[] coffes = new BaseCoffeItem[100];
    private int curPosition = 0;

    /**
     * Конструктор класса
     * @param input
     */
    public InputManager(Input input) {
        this.input = input;
    }

    public int getValueOfItems() {
        return this.curPosition;
    }

    public void getAllCoffes() {
        this.coffes[this.curPosition++] = new BaseCoffeItem("Капучино", 20, 1);
        this.coffes[this.curPosition++] = new BaseCoffeItem("Лотте", 18, 2);
        this.coffes[this.curPosition++] = new BaseCoffeItem("Экспрессо", 17, 3);
        this.coffes[this.curPosition++] = new BaseCoffeItem("Американо", 23, 4);
    }

    public void showMenu() {
        System.out.println("Доступные кофе в данный момент: ");
        for (BaseCoffeItem item : coffes) {
            if (item != null) {
                System.out.println(String.format("%s - %s, цена - %s", item.getKey(), item.getName(), item.getPrice()));
            }
        }
    }

    public void doWork(int key) {
        this.coffes[key - 1].work(this.input);
    }

}
