package services;

import edu.exceptions.CarNotAvailableException;
import edu.exceptions.InvalidDriverAgeException;
import models.Car;
import models.Customer;
import models.Rental;
import repositories.CarRepositoryImpl;
import repositories.Repository;

import java.time.LocalDate;

public class RentalService {

    private final Repository<Car> carRepo;
    private final Repository<Rental> rentalRepo;
    private final PricingService pricingService;

    public RentalService(Repository<Car> carRepo,
                         Repository<Rental> rentalRepo,
                         PricingService pricingService) {

        this.carRepo = carRepo;
        this.rentalRepo = rentalRepo;
        this.pricingService = pricingService;
    }

    public void createRental(Car car, Customer customer, int age, String start, String end)
            throws CarNotAvailableException, InvalidDriverAgeException {

    }

    public void createRental(Car selectedCar, Customer customer, String startStr, String endStr) {
    }
}
