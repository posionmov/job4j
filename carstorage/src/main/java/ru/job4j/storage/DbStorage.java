package ru.job4j.storage;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.models.Body;
import ru.job4j.models.Car;
import ru.job4j.models.Engine;
import ru.job4j.models.Transmission;

import java.util.List;
import java.util.function.Function;

/**
 * Класс для работы с БД
 * @author Galanov Sergey
 * @since 23.10.2018
 * @version 1.0
 */
public class DbStorage implements Store {

    /**
     * Приватные поля класса
     * Содержат:
     *      приватную статическую ссылку на обьект фабрики сессий
     *      публичную статическую ссылку на обьект данного класса
     */
    private static final SessionFactory SOURCE = new Configuration().configure().buildSessionFactory();
    public static final DbStorage INSTANCE = new DbStorage();

    /**
     * Метод полуения из БД всех обьектов класса Car
     * @return коллекция обьектов класса Car
     */
    @Override
    public List<Car> getAllCars() {
        List<Car> cars = this.tx(session -> session.createQuery("from Car ").list());
        return cars;
    }

    /**
     * Метод полуения из БД всех обьектов класса Body
     * @return коллекция обьектов класса Body
     */
    @Override
    public List<Body> getAllBody() {
        List<Body> bodies = this.tx(session -> session.createQuery("from Body ").list());
        return bodies;
    }

    /**
     * Метод полуения из БД всех обьектов класса Engine
     * @return коллекция обьектов класса Engine
     */
    @Override
    public List<Engine> getAllEngine() {
        List<Engine> engines = this.tx(session -> session.createQuery("from Engine ").list());
        return engines;
    }

    /**
     * Метод полуения из БД всех обьектов класса Transmission
     * @return коллекция обьектов класса Transmission
     */
    @Override
    public List<Transmission> getAllTransmissions() {
        List<Transmission> transmissions = this.tx(session -> session.createQuery("from Transmission ").list());
        return transmissions;
    }

    /**
     * Метод добавления в БД обьекта класса Car
     * @param car
     * @return
     */
    @Override
    public int addCar(Car car) {
        int id = this.tx(session -> {
                                    session.save(car);
                                    return car.getId();
        });
        return id;
    }

    /**
     * Метод добавления в БД обьекта класса Body
     * @param body
     * @return
     */
    @Override
    public int addBody(Body body) {
        return this.tx(session -> {
                                    session.save(body);
                                    return body.getId();
        });
    }

    /**
     * Метод добавления в БД обьекта класса Engine
     * @param engine
     * @return
     */
    @Override
    public int addEngine(Engine engine) {
        return this.tx(session -> {
                                    session.save(engine);
                                    return engine.getId();
        });
    }

    /**
     * Метод добавления в БД обьекта класса Transmission
     * @param transmission
     * @return
     */
    @Override
    public int addTransmission(Transmission transmission) {
        return this.tx(session -> {
                                    session.save(transmission);
                                    return transmission.getId();
        });
    }

    /**
     * Метод обновления в Бд обьекта класса Car
     * @param car
     * @return
     */
    @Override
    public boolean updateCar(Car car) {
        return this.tx(session -> {
            session.saveOrUpdate(car);
            return true;
        });
    }

    /**
     * Метод обновления в Бд обьекта класса Body
     * @param body
     * @return
     */
    @Override
    public boolean updateBody(Body body) {
        return this.tx(session -> {
            session.saveOrUpdate(body);
            return true;
        });
    }

    /**
     * Метод обновления в Бд обьекта класса Engine
     * @param engine
     * @return
     */
    @Override
    public boolean updateEngine(Engine engine) {
        return this.tx(session -> {
            session.saveOrUpdate(engine);
            return true;
        });
    }

    /**
     * Метод обновления в Бд обьекта класса Transmission
     * @param transmission
     * @return
     */
    @Override
    public boolean updateTransmission(Transmission transmission) {
        return this.tx(session -> {
            session.saveOrUpdate(transmission);
            return true;
        });
    }

    /**
     * Метод удаления из Бд обьекта класса Car
     * @param car
     * @return
     */
    @Override
    public boolean deleteCar(Car car) {
        return this.tx(session -> {
            session.delete(car);
            return true;
        });
    }

    /**
     * Метод удаления из Бд обьекта класса Body
     * @param body
     * @return
     */
    @Override
    public boolean deleteBody(Body body) {
        return this.tx(session -> {
            session.delete(body);
            return true;
        });
    }

    /**
     * Метод удаления из Бд обьекта класса Engine
     * @param engine
     * @return
     */
    @Override
    public boolean deleteEngine(Engine engine) {
        return this.tx(session -> {
            session.delete(engine);
            return true;
        });
    }

    /**
     * Метод удаления из Бд обьекта класса Transmission
     * @param transmission
     * @return
     */
    @Override
    public boolean deleteTransmission(Transmission transmission) {
        return this.tx(session -> {
            session.delete(transmission);
            return true;
        });
    }

    /**
     * Метод получения из Бд обьекта класса Car по его id
     * @param carId
     * @return
     */
    @Override
    public Car getCarFromId(int carId) {
        return this.tx(session -> {
            Car car = session.get(Car.class, carId);
            return car;
        });
    }

    /**
     * Метод, принимающий в себя лямбда выражение и производящий её выполненеи
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<Session, T> command) {
        Session session = SOURCE.openSession();
        session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.getTransaction().commit();
            session.close();
        }
    }
}
