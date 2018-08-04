package ru.job4j.taskoop;

/**
 * Класс, производящий работу автомата по выдче кофе и сдачи
 * @author Galanov Sergey
 * @since 04.08.2018
 * @version 1.0
 */
public class CofeMachine {

    /**
     * Содержит все необходимые поля
     */
    private InputManager inputManager; // Ссылка на обработчик типы кофе и его
    private boolean isOver = false; // Булевая переменная, отражающая конец выполнения работы автомата
    private InputInterface input; // Ссылка на обработчик ввода данных, принимает все обьекты, которые наследутся от интерфейся InputInterface
    private int keys; // Текущая длина массива, который содержит действия с кофемашиной

    /**
     * Конструктор данного класса
     * @param input обьект класса - обработчика ввода
     */
    public CofeMachine(InputInterface input) {
        this.input = input;
    }

    public static void main(String[] args) {
        new CofeMachine(new ValidateInput(new Input())).init();
    }

    /**
     * Функция, которая устанавливает все значения и прогоняет по кругу выбор коффе пока не будет выключена
     */
    public void init() {
        this.inputManager = new InputManager(input, this);
        this.inputManager.getAllCoffes();
        this.keys = this.inputManager.getKeys();
        do {
            this.inputManager.showMenu();
            this.inputManager.doWork(this.input.userChoise("Что вы выбираете?", this.keys));
        } while (!isOver);
    }

    /**
     * Функция, которая останавливает работу кофемашины
     * При вызове меняет булевую переменную на false, что останавливает бесконечный цикл
     */
    public void stopWork() {
        this.isOver = true;
    }
}
