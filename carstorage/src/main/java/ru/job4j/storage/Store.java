package ru.job4j.storage;

import ru.job4j.models.Body;
import ru.job4j.models.Car;
import ru.job4j.models.Engine;
import ru.job4j.models.Transmission;

import java.util.List;

/**
 * Интерфейс для работы с БД
 * @author Ganaov Sergey
 * @since 23.10.2018
 * @version 1.0
 */
public interface Store {

    List<Car> getAllCars();
    List<Body> getAllBody();
    List<Engine> getAllEngine();
    List<Transmission> getAllTransmissions();

    int addCar(Car car);
    int addBody(Body body);
    int addEngine(Engine engine);
    int addTransmission(Transmission transmission);

    boolean updateCar(Car car);
    boolean updateBody(Body body);
    boolean updateEngine(Engine engine);
    boolean updateTransmission(Transmission transmission);

    boolean deleteCar(Car car);
    boolean deleteBody(Body body);
    boolean deleteEngine(Engine engine);
    boolean deleteTransmission(Transmission transmission);

    Car getCarFromId(int carId);

}
