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

/**
 * Класс-синглтон для работы с БД
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class DbStore implements Store {

    /**
     * Статические final поля класса:
     *      фабрика подключений
     *      обьект данного класса
     */
    private final static SessionFactory SOURCE = new Configuration().configure().buildSessionFactory();
    public final static DbStore INSTANCE = new DbStore();

    /**
     * Метод добавления модели автомобиля
     * @param model
     * @return
     */
    @Override
    public int addModel(CarModel model) {
        return this.tx(session -> {
            session.save(model);
            return model.getId();
        });
    }

    /**
     * Метод добавления кузова автомобиля
     * @param bodyType - кузов автомобиля
     * @return id добавленного кузова
     */
    @Override
    public int addBody(CarBodyType bodyType) {
        return this.tx(session -> {
            session.save(bodyType);
            return bodyType.getId();
        });
    }

    /**
     * Метод добавления коробки передач автомобиля
     * @param transmission - коробка передач автомобиля
     * @return id добавленной коробки передач
     */
    @Override
    public int addTransmission(CarTransmission transmission) {
        return this.tx(session -> {
            session.save(transmission);
            return transmission.getId();
        });
    }

    /**
     * Метод добавления двигателя
     * @param engine - двигатель
     * @return id добавленного двигателя
     */
    @Override
    public int addEngine(CarEngine engine) {
        return this.tx(session -> {
            session.save(engine);
            return engine.getId();
        });
    }

    /**
     * Мтод добавления привода
     * @param drive - привод
     * @return id добавленного привода
     */
    @Override
    public int addDrive(CarDrive drive) {
        return this.tx(session -> {
            session.save(drive);
            return drive.getId();
        });
    }

    /**
     * Метод добавления цвета автомобиля
     * @param carColor - цвет автомобиля
     * @return id добавленного цвета
     */
    @Override
    public int addCarColor(CarColor carColor) {
        return this.tx(session -> {
            session.save(carColor);
            return carColor.getId();
        });
    }

    /**
     * Метод добавления марки автомобиля
     * @param mark - марка автомобиля для добавления
     * @return id добавленной марки автомобиля
     */
    @Override
    public int addMark(CarMark mark) {
        return this.tx(session -> {
            session.save(mark);
            return mark.getId();
        });
    }

    /**
     * Метод добавления права доступа
     * @param role - право доступа на добавление
     * @return id добавленного права доступа
     */
    @Override
    public int addRole(Role role) {
        return this.tx(session -> {
            session.save(role);
            return role.getId();
        });
    }

    /**
     * Метод добавления автомобиля
     * @param car - автомобиль для добавления
     * @return добавленный автомобиль
     */
    @Override
    public Car addCar(Car car) {
        return this.tx(session -> {
            session.save(car);
            return car;
        });
    }

    /**
     * Метод добавления обьявления
     * @param ad - обьявление для добавления
     * @return созданное обьявление
     */
    @Override
    public Advertisement addAd(Advertisement ad) {
        Advertisement result = this.tx(session -> {
            session.save(ad);
            return ad;
        });
        result = this.addPathToImage(result);
        return result;
    }

    /**
     * Приватный метод добавления пути к картинке к обьявлению
     * @param ad - обьявление, в которое необходимо добавить путь до картинки на сервере
     * @return обновленное обьявление
     */
    private Advertisement addPathToImage(Advertisement ad) {
        ad.setPathToImage("/images/" + ad.getId() + ".jpg");
        Session session = SOURCE.openSession();
        session.beginTransaction();
        session.update(ad);
        session.getTransaction().commit();
        session.close();
        return ad;
    }

    /**
     * Метод добавления пользователя
     * @param user - пользователь для добавления
     * @return созданный пользователь
     */
    @Override
    public User addUser(User user) {
        return this.tx(session -> {
            session.save(user);
            return user;
        });
    }

    /**
     * Метод проверки существования пользователя в БД
     * @param user пользователь для проверки
     * @return true если пользователь существует
     */
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

    /**
     * Метод удаления обьявления из БД
     * @param ad - обьявление для удаления
     * @return true если обьявление удачно удалено
     */
    @Override
    public boolean deleteAd(Advertisement ad) {
        boolean result = false;
        Session session = SOURCE.openSession();
        session.beginTransaction();
        try {
            ad = session.get(Advertisement.class, ad.getId());
            session.delete(ad);
            session.delete(ad.getCar());
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
            result = false;
        } finally {
            if (!result) {
                session.getTransaction().rollback();
            } else {
                session.getTransaction().commit();
            }
            session.close();
        }
        return result;
    }

    /**
     * Метод закрытия обьявления
     * @param ad - обьявление для закрытия
     * @return true если обьявление удачно закрыто
     */
    @Override
    public boolean closeAd(Advertisement ad) {
        boolean result = false;
        Session session = SOURCE.openSession();
        session.beginTransaction();
        try {
            ad = session.get(Advertisement.class, ad.getId());
            ad.setClose(true);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (!result) {
                session.getTransaction().rollback();
            } else {
                session.getTransaction().commit();
            }
            session.close();
        }
        return result;
    }

    /**
     * Метод получения всех автомобилей
     * @return коллекция автомобилей
     */
    @Override
    public List<Car> getAllCars() {
        return tx(session -> session.createQuery("from Car ").list());
    }

    /**
     * Метод получения всех марок автомобилей
     * @return коллекция марок автомобилей
     */
    @Override
    public List<CarMark> getAllMarks() {
        return tx(session -> session.createQuery("from CarMark ").list());
    }

    /**
     * Метод получения всех кузовов автомобилей
     * @return коллекция типов кузовов
     */
    @Override
    public List<CarBodyType> getAllBodyTypes() {
        return tx(session -> session.createQuery("from CarBodyType ").list());
    }

    /**
     * Метод получения всех коробок передач
     * @return коллекция коробок передач
     */
    @Override
    public List<CarTransmission> getAllTransmissions() {
        return tx(session -> session.createQuery("from CarTransmission ").list());
    }

    /**
     * Метод получения всех двигателей автомобилей
     * @return коллекция двигателей
     */
    @Override
    public List<CarEngine> getAllEngines() {
        return tx(session -> session.createQuery("from CarEngine ").list());
    }

    /**
     * Метод получения всех приводов автомобилей
     * @return коллекция приводов
     */
    @Override
    public List<CarDrive> getAllDrives() {
        return tx(session -> session.createQuery("from CarDrive ").list());
    }

    /**
     * Метод получения всех цветов автомобилей
     * @return коллекция цветов автомобилей
     */
    @Override
    public List<CarColor> getAllColors() {
        return tx(session -> session.createQuery("from CarColor ").list());
    }

    /**
     * Метод получения всех ролей
     * @return коллекция ролей
     */
    @Override
    public List<Role> getAllRoles() {
        return tx(session -> session.createQuery("from Role ").list());
    }

    /**
     * Метод получения всех обьявлений
     * @return коллекция обьявлений
     */
    @Override
    public List<Advertisement> getAllAd() {
        return tx(session -> session.createQuery("from Advertisement ").list());
    }

    /**
     * Метод получения всех пользователей
     * @return коллекция пользователей
     */
    @Override
    public List<User> getAllUsers() {
        return tx(session -> session.createQuery("from User ").list());
    }

    /**
     * Метод поиска по обьявлениям
     * @param ad - обьявление с заполненными полями пользователем
     * @param priceFrom - цена от
     * @param priceTo - цена до
     * @return коллекция найденных обьявлений
     */
    @Override
    public List<Advertisement> findAd(Advertisement ad, int priceFrom, int priceTo) {
        List<Advertisement> result = null;
        String query = "from Advertisement ad where ";
        query += ad.getCar().getMark().getId() != 0 ? " ad.car.mark.id = " + ad.getCar().getMark().getId() + " and" : "";
        query += ad.getCar().getCarModel().getId() != 0 ? " ad.car.carModel.id = " + ad.getCar().getCarModel().getId() + " and" : "";
        query += ad.getCar().getBodyType().getId() != 0 ? " ad.car.bodyType.id = " + ad.getCar().getBodyType().getId() + " and" : "";
        query += ad.getCar().getYearOfManufactured() != 0 ? " ad.car.yearOfManufactured >= " + ad.getCar().getYearOfManufactured() + " and" : "";
        query += ad.getCar().getMileage() != 0 ? " ad.car.mileage >= " + ad.getCar().getMileage() + " and" : "";
        query += ad.getCar().getTransmission().getId() != 0 ? " ad.car.transmission.id = " + ad.getCar().getTransmission().getId() + " and" : "";
        query += ad.getCar().getEngine().getId() != 0 ? " ad.car.engine.id = " + ad.getCar().getEngine().getId() + " and" : "";
        query += ad.getCar().getEngineCapacity() != 0 ? " ad.car.engineCapacity >= " + ad.getCar().getEngineCapacity() + " and" : "";
        query += ad.getCar().getPower() != 0 ? " ad.car.power >= " + ad.getCar().getPower() + " and" : "";
        query += ad.getCar().getDrive().getId() != 0 ? " ad.car.drive.id = " + ad.getCar().getDrive().getId() + " and" : "";
        query += " ad.car.leftRudder = " + ad.getCar().isLeftRudder() + " and";
        query += ad.getCar().getCarColor().getId() != 0 ? " ad.car.carColor.id = " + ad.getCar().getCarColor().getId() + " and" : "";
        query += priceFrom != 0 ? " ad.car.price >= " + priceFrom + " and" : "";
        query += priceTo != 0 ? " ad.car.price <= " + priceTo + " and" : "";
        query = query.substring(0, query.length() - 3);
        String finalQuery = query;
        List dbData = this.tx(session -> {
            Query res = session.createQuery(finalQuery);
            return res.list();
        });
        result = dbData;
        return result;
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
