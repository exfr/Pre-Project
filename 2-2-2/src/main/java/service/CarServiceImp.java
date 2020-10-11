package service;

import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImp implements CarService {
    public List<Car> getAllCars() {
        List<Car> carList = new ArrayList<>();

        carList.add(new Car("Car1", 111, 111));
        carList.add(new Car("Car2", 222, 222));
        carList.add(new Car("Car3", 333, 333));
        return carList;
    }
}
