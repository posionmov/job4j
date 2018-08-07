package ru.job4j.bank;

/**
 * Класс, описывающий счет пользователя
 * @author Galanov Sergey
 * @since 07.08.2018
 * @version 1.0
 */
public class Account {

    private double value; //Кол-во денег
    private int requisites; //№ счета в банке

    /**
     * Стандарный конструктор класса
     * @param value - количество денег на счете
     * @param requisites - реквизит счета (№ счета)
     */
    public Account(int value, int requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    /**
     * Метод-гетер суммы на счете
     * @return сумма на счете
     */
    public double getValue() {
        return this.value;
    }

    /**
     * Метод-геттер реквизита счета
     * @return реквизит счета (№ счета)
     */
    public int getRequisites() {
        return this.requisites;
    }

    /**
     * Метод, увеличивающий текущий счет на велечину, передаваемую в параметре
     * @param  сумма, на которую стоит увеличить счет
     */
    public void increaseValue(double amount) {
        this.value += amount;
    }

    /**
     * Метод, уменьшающий текущий счет на велечину, передаваемую в параметре
     * @param amount - величина, на которую следует уменьшить текущий счет
     */
    public void decreaseValue(double amount) {
        this.value -= amount;
    }

}
