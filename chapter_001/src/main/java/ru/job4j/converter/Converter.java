package ru.job4j.converter;

/**
 * Class for converting rub to euro and dollars
 * @author Sergey Galanov
 * @since 20.07.2018
 * @version 1.1
 */
public class Converter {

    /**
     * contains prive euro and dollar in rub
     */
    private double euro = 70;
    private double dollar = 60;

    /**
     * Func for convertation rubles into dollars
     * @param rub - value of rub for convertating in dollar
     * @return quantity of dollars in this quantity of rubbles
     */
    public double rubToDollar(double rub) {
        return rub / dollar;
    }

    /**
     * Func for convertation rubles into Euro
     * @param rub - value of rub for convertating in dollar
     * @return quantity of Euro in this quantity of rubbles
     */
    public double rubToEuro(double rub) {
        return rub / euro;
    }

    /**
     * Func for convertation Dollars into Rubles
     * @param dollarAmount - quantity of dollars
     * @return quantity of rubles
     */
    public double dollarToRub(double dollarAmount) {
        return dollar * dollarAmount;
    }

    /**
     * Func for convertation Euros in Rubles
     * @param euroAmount - quantity of Euros
     * @return quantity of rubles
     */
    public double euroToRub(double euroAmount) {
        return euro * euroAmount;
    }
}
