package ru.job4j.condition;

/**
 * class for question from theme "Triangle"
 * @author Sergey Galanov
 * @since 21.07.2018
 * @version 1.0
 */
public class Triangle {

    /**
     * private variables for points A, B and C
     * all variables are type Point
     */
    private Point a;
    private Point b;
    private Point c;

    /**
     * Constructor for this class
     * @param a - X and Y for first point
     * @param b - X and Y for second point
     * @param c - X and Y for last point
     */
    public Triangle(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    /**
     * Func for calculating half of perimeter
     * @param ab - distance between a and b
     * @param ac - distance betweel a and c
     * @param bc - distance between b and c
     * @return half of perimeter
     */
    public double period(double ab, double ac, double bc) {
        return (ab + ac + bc) / 2;
    }

    /**
     * Func for checking opportunity create triangle for this points
     * @param ab - distance between a and b
     * @param ac - distance between a and c
     * @param bc - distance between b and c
     * @return
     */
    private boolean exists(double ab, double ac, double bc) {
        if (ab < 0 || ac < 0 || bc < 0) {
            return false;
        } else {
            return !((ab + bc) < ac) && !((ab + ac) < bc) && !((bc + ac) < ab);
        }
    }

    /**
     * Func for finding area of Triangle
     * @return
     */
    public double area() {

        double rsl = -1;
        double ab = this.a.distanceTo(b);
        double ac = this.a.distanceTo(c);
        double bc = this.b.distanceTo(c);

        double p = this.period(ab, ac, bc);

        if (this.exists(ab, ac, bc)) {
            rsl = Math.sqrt(p * (p - ab) * (p - bc) * (p - ac));
        }
        return rsl;
    }
}
