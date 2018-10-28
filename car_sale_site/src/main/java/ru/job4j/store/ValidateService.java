package ru.job4j.store;

import ru.job4j.models.Advertisement;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;

import java.util.List;

/**
 * Класс-синглтон для работы с БД
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public enum  ValidateService implements Store {

    INSTANCE;

    /**
     * Метод добавления модели автомобиля
     * @param model - модель автомобиля
     * @return id добавоенной модели
     */
    @Override
    public int addModel(CarModel model) {
        return DbStore.INSTANCE.addModel(model);
    }

    /**
     * Метод добавления кузова автомобиля
     * @param bodyType - кузов автомобиля
     * @return id добавленного кузова
     */
    @Override
    public int addBody(CarBodyType bodyType) {
        return DbStore.INSTANCE.addBody(bodyType);
    }

    /**
     * Метод добавления коробки передач автомобиля
     * @param transmission - коробка передач автомобиля
     * @return id добавленной коробки передач
     */
    @Override
    public int addTransmission(CarTransmission transmission) {
        return DbStore.INSTANCE.addTransmission(transmission);
    }

    /**
     * Метод добавления двигателя
     * @param engine - двигатель
     * @return id добавленного двигателя
     */
    @Override
    public int addEngine(CarEngine engine) {
        return DbStore.INSTANCE.addEngine(engine);
    }

    /**
     * Метод добавления привода
     * @param drive - привод
     * @return id добавленного привода
     */
    @Override
    public int addDrive(CarDrive drive) {
        return DbStore.INSTANCE.addDrive(drive);
    }

    /**
     * Метод добавления цвета автомобиля
     * @param carColor - цвет автомобиля
     * @return id добавленного цвета
     */
    @Override
    public int addCarColor(CarColor carColor) {
        return DbStore.INSTANCE.addCarColor(carColor);
    }

    /**
     * Метод добавления марки автомобиля
     * @param mark - марка автомобиля для добавления
     * @return id добавленной марки автомобиля
     */
    @Override
    public int addMark(CarMark mark) {
        return DbStore.INSTANCE.addMark(mark);
    }

    /**
     * Метод добавления права доступа
     * @param role - право доступа на добавление
     * @return id добавленного права доступа
     */
    @Override
    public int addRole(Role role) {
        return DbStore.INSTANCE.addRole(role);
    }

    /**
     * Метод добавления автомобиля
     * @param car - автомобиль для добавления
     * @return добавленный автомобиль
     */
    @Override
    public Car addCar(Car car) {
        return DbStore.INSTANCE.addCar(car);
    }

    /**
     * Метод добавления обьявления
     * @param ad - обьявление для добавления
     * @return созданное обьявление
     */
    @Override
    public Advertisement addAd(Advertisement ad) {
        return DbStore.INSTANCE.addAd(ad);
    }

    /**
     * Метод добавления пользователя
     * @param user - пользователь для добавления
     * @return созданный пользователь
     */
    @Override
    public User addUser(User user) {
        return DbStore.INSTANCE.addUser(user);
    }

    /**
     * Метод получения всех автомобилей
     * @return коллекция автомобилей
     */
    @Override
    public List<Car> getAllCars() {
        return DbStore.INSTANCE.getAllCars();
    }

    /**
     * Метод получения всех марок автомобилей
     * @return коллекция марок автомобилей
     */
    @Override
    public List<CarMark> getAllMarks() {
        return DbStore.INSTANCE.getAllMarks();
    }

    /**
     * Метод получения всех кузовов автомобилей
     * @return коллекция типов кузовов
     */
    @Override
    public List<CarBodyType> getAllBodyTypes() {
        return DbStore.INSTANCE.getAllBodyTypes();
    }

    /**
     * Метод получения всех коробок передач
     * @return коллекция коробок передач
     */
    @Override
    public List<CarTransmission> getAllTransmissions() {
        return DbStore.INSTANCE.getAllTransmissions();
    }

    /**
     * Метод получения всех двигателей автомобилей
     * @return коллекция двигателей
     */
    @Override
    public List<CarEngine> getAllEngines() {
        return DbStore.INSTANCE.getAllEngines();
    }

    /**
     * Метод получения всех приводов автомобилей
     * @return коллекция приводов
     */
    @Override
    public List<CarDrive> getAllDrives() {
        return DbStore.INSTANCE.getAllDrives();
    }

    /**
     * Метод получения всех цветов автомобилей
     * @return коллекция цветов автомобилей
     */
    @Override
    public List<CarColor> getAllColors() {
        return DbStore.INSTANCE.getAllColors();
    }

    /**
     * Метод получения всех ролей
     * @return коллекция ролей
     */
    @Override
    public List<Role> getAllRoles() {
        return DbStore.INSTANCE.getAllRoles();
    }

    /**
     * Метод получения всех обьявлений
     * @return коллекция обьявлений
     */
    @Override
    public List<Advertisement> getAllAd() {
        return DbStore.INSTANCE.getAllAd();
    }

    /**
     * Метод получения всех пользователей
     * @return коллекция пользователей
     */
    @Override
    public List<User> getAllUsers() {
        return DbStore.INSTANCE.getAllUsers();
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
        return DbStore.INSTANCE.findAd(ad, priceFrom, priceTo);
    }

    /**
     * Метод проверки существования пользователя в БД
     * @param user пользователь для проверки
     * @return true если пользователь существует
     */
    @Override
    public User checkUser(User user) {
        return DbStore.INSTANCE.checkUser(user);
    }

    /**
     * Метод удаления обьявления из БД
     * @param ad - обьявление для удаления
     * @return true если обьявление удачно удалено
     */
    @Override
    public boolean deleteAd(Advertisement ad) {
        return DbStore.INSTANCE.deleteAd(ad);
    }

    /**
     * Метод закрытия обьявления
     * @param ad - обьявление для закрытия
     * @return true если обьявление удачно закрыто
     */
    @Override
    public boolean closeAd(Advertisement ad) {
        return DbStore.INSTANCE.closeAd(ad);
    }
}
