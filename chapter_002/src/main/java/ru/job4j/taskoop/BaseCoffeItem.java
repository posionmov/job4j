package ru.job4j.taskoop;

public class BaseCoffeItem {

    private String name;
    private int price;
    private int key;

    protected BaseCoffeItem(String name, int price, int key) {
        this.name = name;
        this.price = price;
        this.key = key;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getKey() {
        return this.key;
    }

    public int[] work(Input input) {
        String.format("Цена данного напитка - %s", this.price);
        int moneyIn = input.userChoise("Какую купюру вы вставите?", 5000);
        int[] result = new Change().change(moneyIn, this.price);
        for (int i = 0; i != result.length; i++) {
            System.out.println(result[i]);
        }
        return result;
    }



}
