package ru.job4j.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.models.Advertisement;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;

import java.util.List;
import java.util.function.Function;

public class DbStore implements Store {

    private final static SessionFactory SOURCE = new Configuration().configure().buildSessionFactory();
    public final static DbStore INSTANCE = new DbStore();

    @Override
    public int addModel(CarModel model) {
        return this.tx(session -> {
            session.save(model);
            return model.getId();
        });
    }

    @Override
    public int addBody(CarBodyType bodyType) {
        return this.tx(session -> {
            session.save(bodyType);
            return bodyType.getId();
        });
    }

    @Override
    public int addTransmission(CarTransmission transmission) {
        return this.tx(session -> {
            session.save(transmission);
            return transmission.getId();
        });
    }

    @Override
    public int addEngine(CarEngine engine) {
        return this.tx(session -> {
            session.save(engine);
            return engine.getId();
        });
    }

    @Override
    public int addDrive(CarDrive drive) {
        return this.tx(session -> {
            session.save(drive);
            return drive.getId();
        });
    }

    @Override
    public int addCarColor(CarColor carColor) {
        return this.tx(session -> {
            session.save(carColor);
            return carColor.getId();
        });
    }

    @Override
    public int addMark(CarMark mark) {
        return this.tx(session -> {
            session.save(mark);
            return mark.getId();
        });
    }

    @Override
    public int addRole(Role role) {
        return this.tx(session -> {
            session.save(role);
            return role.getId();
        });
    }

    @Override
    public Car addCar(Car car) {
        return this.tx(session -> {
            session.save(car);
            return car;
        });
    }

    @Override
    public Advertisement addAd(Advertisement ad) {
        return this.tx(session -> {
            session.save(ad);
            return ad;
        });
    }

    @Override
    public User addUser(User user) {
        return this.tx(session -> {
            session.save(user);
            return user;
        });
    }

    // Метод проверки существования пользователя
    @Override
    public User checkUser(User user) {
        User result = null;
        List list = this.tx(session -> {
            Query query = session.createQuery("from User where login = :login and password = :password");
            query.setParameter("login", user.getLogin());
            query.setParameter("password", user.getPassword());
            return query.list();
        });
        if (list.size() == 1) {
            result = (User) list.get(0);
        }
        return result;
    }

    @Override
    public List<Car> getAllCars() {
        return tx(session -> session.createQuery("from Car ").list());
    }

    @Override
    public List<CarMark> getAllMarks() {
        return tx(session -> session.createQuery("from CarMark ").list());
    }

    @Override
    public List<CarBodyType> getAllBodyTypes() {
        return tx(session -> session.createQuery("from CarBodyType ").list());
    }

    @Override
    public List<CarTransmission> getAllTransmissions() {
        return tx(session -> session.createQuery("from CarTransmission ").list());
    }

    @Override
    public List<CarEngine> getAllEngines() {
        return tx(session -> session.createQuery("from CarEngine ").list());
    }

    @Override
    public List<CarDrive> getAllDrives() {
        return tx(session -> session.createQuery("from CarDrive ").list());
    }

    @Override
    public List<CarColor> getAllColors() {
        return tx(session -> session.createQuery("from CarColor ").list());
    }

    @Override
    public List<Role> getAllRoles() {
        return tx(session -> session.createQuery("from Role ").list());
    }

    @Override
    public List<Advertisement> getAllAd() {
        return tx(session -> session.createQuery("from Advertisement ").list());
    }

    @Override
    public List<User> getAllUsers() {
        return tx(session -> session.createQuery("from User ").list());
    }

    /**
     * Метод, принимающий в себя лямбда выражение и производящий её выполненеи
     * @param command
     * @param <T>
     * @return
     */
    private <T> T tx(final Function<Session, T> command) {
        Session session = SOURCE.openSession();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
