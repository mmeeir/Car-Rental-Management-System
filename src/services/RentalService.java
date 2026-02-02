package services;

import edu.exceptions.CarNotAvailableException;
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

    public RentalService(Repository<Car> carRepo, Repository<Rental> rentalRepo, PricingService pricingService) {
        this.carRepo = carRepo;
        this.rentalRepo = rentalRepo;
        this.pricingService = pricingService;
    }

    public void createRental(Car car, Customer customer, String start, String end) throws CarNotAvailableException {

        if (!car.isAvailable()) {
            throw new CarNotAvailableException("Car " + car.getModel() + " is already booked!");
        }


        double cost = pricingService.calculateCost(LocalDate.parse(start), LocalDate.parse(end), car.getPricePerDay());


        Rental rental = new Rental.Builder()
                .setCarId(car.getId())
                .setCustomerId(customer.getId())
                .setStartDate(LocalDate.parse(start))
                .setEndDate(LocalDate.parse(end))
                .setTotalCost(cost)
                .setStatus("ACTIVE")
                .build();


        rentalRepo.add(rental);


        ((CarRepositoryImpl) carRepo).updateAvailability(car.getId(), false);

        System.out.println("Rental successfully created via Builder! Total: $" + cost);
    }
}