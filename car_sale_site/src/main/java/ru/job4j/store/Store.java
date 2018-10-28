package ru.job4j.store;

import ru.job4j.models.Advertisement;
import ru.job4j.models.Role;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;

import java.util.List;

/**
 * Интерфейс для работы с БД
 * @author Galanov Sergey
 * @since
 * @version 1.0
 */
public interface Store {
    int addModel(CarModel model);
    int addBody(CarBodyType bodyType);
    int addTransmission(CarTransmission transmission);
    int addEngine(CarEngine engine);
    int addDrive(CarDrive drive);
    int addCarColor(CarColor carColor);
    int addMark(CarMark mark);
    int addRole(Role role);
    Car addCar(Car car);
    Advertisement addAd(Advertisement ad);
    User addUser(User user);

    List<Car> getAllCars();
    List<CarMark> getAllMarks();
    List<CarBodyType> getAllBodyTypes();
    List<CarTransmission> getAllTransmissions();
    List<CarEngine> getAllEngines();
    List<CarDrive> getAllDrives();
    List<CarColor> getAllColors();
    List<Role> getAllRoles();
    List<Advertisement> getAllAd();
    List<User> getAllUsers();

    List<Advertisement> findAd(Advertisement ad, int priceFrom, int priceTo);
    User checkUser(User user);

    boolean deleteAd(Advertisement ad);
    boolean closeAd(Advertisement ad);
}
