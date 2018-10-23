package ru.job4j.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        Session session = factory.openSession();
        session.beginTransaction();

//        Transmission body = new Transmission();
//        body.setDescription("Transmission1");
//        session.save(body);
//////
//        Car car = new Car();
//        car.setDescription("carDesc");
//        car.setBody(new Body(1));
//        car.setEngine(new Engine(1));
//        car.setTransmission(new Transmission(1));
//        session.save(car);
//
////        System.out.println(car);
//
//        Body body1 = session.get(Body.class, 1);
//
//        Car car = new Car();
//        car.setDescription("carDesc");
//        car.setBody(body1);
//        session.save(car);
//
        Car car = session.get(Car.class, 1);
        if (car.getEngine().getDescription().equals("engine1")) {
            System.out.println("Я ПОБЕДИЛ ЭТО ГОВНО!!!");
        }

        //toString() не обязателен, тут у мея получилось победить
        //todo Сделать методы более классными

        System.out.println(123);
        session.getTransaction().commit();
        session.close();
        factory.close();
    }
}
