package ru.job4j.professions;

public class Ingener extends Profession {

    public House build(int[] parameters) {
        House house = null;
        if (parameters.length < 3) {
            house = new House(parameters[1], parameters[2], parameters[3]);
        } else {
            System.out.println("Введите 3 числа");
        }

        return house;
    }
}
