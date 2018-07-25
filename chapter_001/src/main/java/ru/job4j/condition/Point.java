package ru.job4j.condition;


/**
 * Class for calculate distance between points
 * @author Sergey Galanov
 * @since 20.07.2018
 * @version 1.0
 */
public class Point {

    /**
     * Contains X and Y coordinates
     */
    private int x;
    private int y;

    /**
     * Constructor for this class
     * Contains first X and Y coordinates
     * @param x
     * @param y
     */
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Func for find distance between this point and another one
     * @param that - second point
     * @return distance between points
     */
    public double distanceTo(Point that) {
        Point a = this;
        Point b = that;
        return Math.sqrt(Math.pow(b.x - a.x, 2) + Math.pow(b.y - a.y, 2));
    }

    public static void main(String[] args) {
        Point a = new Point(5, 3);
        Point b = new Point(2, 6);
        System.out.println("Point a = " + a.x + " " + a.y);
        System.out.println("Point b = " + b.x + " " + b.y);
        System.out.println("Расстояние между точками А и Б = " + a.distanceTo(b));
    }
}
