package ru.job4j.storage;

import ru.job4j.models.Body;
import ru.job4j.models.Car;
import ru.job4j.models.Engine;
import ru.job4j.models.Transmission;

import java.util.List;

public enum  ValidateService implements Store {

    INSTANCE;

    @Override
    public List<Car> getAllCars() {
        return null;
    }

    @Override
    public List<Body> getAllBody() {
        return null;
    }

    @Override
    public List<Engine> getAllEngine() {
        return null;
    }

    @Override
    public List<Transmission> getAllTransmissions() {
        return null;
    }

    @Override
    public boolean addCar(Car car) {
        return false;
    }

    @Override
    public boolean addBody(Body body) {
        return false;
    }

    @Override
    public boolean addEngine(Engine engine) {
        return false;
    }

    @Override
    public boolean addTransmission(Transmission transmission) {
        return false;
    }

    @Override
    public boolean updateCar(Car car) {
        return false;
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
        return false;
    }

    @Override
    public boolean deleteBody(Body body) {
        return false;
    }

    @Override
    public boolean deleteEngine(Engine engine) {
        return false;
    }

    @Override
    public boolean deleteTransmission(Transmission transmission) {
        return false;
    }
}
