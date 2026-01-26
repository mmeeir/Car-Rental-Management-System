package services;

import edu.exceptions.CarNotAvailableException;
import models.Car;
import models.Customer;
import repositories.CarRepository;
import repositories.RentalRepository;

import java.time.LocalDate;

public class RentalService {
    private final CarRepository carRepo;
    private final RentalRepository rentalRepo;
    private final PricingService pricingService;

    public RentalService(CarRepository carRepo, RentalRepository rentalRepo, PricingService pricingService) {
        this.carRepo = carRepo;
        this.rentalRepo = rentalRepo;
        this.pricingService = pricingService;
    }

    public  void createRental(Car car, Customer customer, String start, String end) throws CarNotAvailableException {
        if (!car.isAvailable()) {
            throw new CarNotAvailableException("Car " + car.getModel() + "is already booked!");
        }
        double cost = pricingService.calculateCost(LocalDate.parse(start), LocalDate.parse(end), car.getPricePerDay());
        rentalRepo.saveRental(car.getId(), customer.getId(), start, end, cost);
        carRepo.updateAvailability(car.getId(), false);
        System.out.println("Rental successfully created! Total: $" + cost);
    }
}