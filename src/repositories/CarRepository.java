package repositories;

import models.Car;

import java.util.List;

public interface CarRepository {
    List<Car> getAllAvailableCars();
    void updateAvailability(int carId, boolean available);
    Car getCarById(int id);

}
