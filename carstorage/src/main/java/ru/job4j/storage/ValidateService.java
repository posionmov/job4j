package ru.job4j.storage;

import ru.job4j.models.Body;
import ru.job4j.models.Car;
import ru.job4j.models.Engine;
import ru.job4j.models.Transmission;

import java.util.List;

/**
 * Класс-синглтон для работы с БД
 * @author Galanov Sergey
 * @since 23.10.2018
 * @version 1.0
 */
public enum  ValidateService implements Store {

    INSTANCE;

    @Override
    public List<Car> getAllCars() {
        return DbStorage.INSTANCE.getAllCars();
    }

    @Override
    public List<Body> getAllBody() {
        return DbStorage.INSTANCE.getAllBody();
    }

    @Override
    public List<Engine> getAllEngine() {
        return DbStorage.INSTANCE.getAllEngine();
    }

    @Override
    public List<Transmission> getAllTransmissions() {
        return DbStorage.INSTANCE.getAllTransmissions();
    }

    @Override
    public int addCar(Car car) {
        return DbStorage.INSTANCE.addCar(car);
    }

    @Override
    public int addBody(Body body) {
        return DbStorage.INSTANCE.addBody(body);
    }

    @Override
    public int addEngine(Engine engine) {
        return DbStorage.INSTANCE.addEngine(engine);
    }

    @Override
    public int addTransmission(Transmission transmission) {
        return DbStorage.INSTANCE.addTransmission(transmission);
    }

    @Override
    public boolean updateCar(Car car) {
        return DbStorage.INSTANCE.updateCar(car);
    }

    @Override
    public boolean updateBody(Body body) {
        return false;
    }

    @Override
    public boolean updateEngine(Engine engine) {
        return false;
    }

    @Override
    public boolean updateTransmission(Transmission transmission) {
        return false;
    }

    @Override
    public boolean deleteCar(Car car) {
        return DbStorage.INSTANCE.deleteCar(car);
    }

    @Override
    public boolean deleteBody(Body body) {
        return DbStorage.INSTANCE.deleteBody(body);
    }

    @Override
    public boolean deleteEngine(Engine engine) {
        return DbStorage.INSTANCE.deleteEngine(engine);
    }

    @Override
    public boolean deleteTransmission(Transmission transmission) {
        return DbStorage.INSTANCE.deleteTransmission(transmission);
    }

    @Override
    public Car getCarFromId(int carId) {
        return DbStorage.INSTANCE.getCarFromId(carId);
    }
}
