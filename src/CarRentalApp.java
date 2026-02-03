import config.FleetConfig;
import edu.exceptions.CarNotAvailableException;
import models.Car;
import models.Customer;
import models.Rental;
import repositories.CarRepositoryImpl;
import repositories.CustomerRepositoryImpl;
import repositories.RentalRepositoryImpl;
import repositories.Repository;
import services.PricingService;
import services.RentalService;

import java.util.List;
import java.util.Scanner;

public class CarRentalApp {
    private final Repository<Car> carRepo = new CarRepositoryImpl();
    private final Repository<Rental> rentalRepo = new RentalRepositoryImpl();
    private final Repository<Customer> customerRepo = new CustomerRepositoryImpl();
    private final PricingService pricingService = new PricingService();
    private final RentalService rentalService = new RentalService(carRepo, rentalRepo, pricingService);

    private Scanner scanner = new Scanner(System.in);


    public void run() {
        System.out.println(
                FleetConfig.getInstance().getCompanyName()
        );
        boolean running = true;
        FleetConfig config = FleetConfig.getInstance();
        while (running) {
            System.out.println("\n===" + config.getCompanyName().toUpperCase() + "===");
            System.out.println("1. View cars");
            System.out.println("2. View rentals");
            System.out.println("3. Create rental");
            System.out.println("4. Complete rental");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> showCars();
                case 2 -> showRentals();
                case 3 -> createRental();
                case 4 -> completeRental();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void showCars() {
        System.out.println("\n--- All Cars from Database ---");
        List<Car> allCars = carRepo.findAll();
        if (allCars.isEmpty()) {
            System.out.println("No cars found in database.");
        } else {
            allCars.forEach(System.out::println);
        }
    }

    private void showRentals() {
        System.out.println("\n--- Current Rentals ---");
        List<Rental> allRentals = rentalRepo.findAll();
        if (allRentals == null || allRentals.isEmpty()) {
            System.out.println("No rentals yet.");
            return;
        }
        allRentals.forEach(System.out::println);
    }

    private void createRental() {
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        int customerId;
        customerId = ((CustomerRepositoryImpl) customerRepo).createCustomerAndGetId(name);

        if (customerId == -1) {
            System.out.println("Error registering client in the database!");
            return;
        }
        Customer customer = new Customer(customerId, name);

        System.out.println("\nAvailable Cars:");
        for (Car car : carRepo.findAll()) {
            System.out.println(car);
        }

        System.out.print("Choose car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = carRepo.findById();
        if (selectedCar == null) {
            System.out.println("Car not found!");
            return;
        }

        System.out.print("Start date (YYYY-MM-DD): ");
        String startStr = scanner.nextLine();
        System.out.print("End date (YYYY-MM-DD): ");
        String endStr = scanner.nextLine();

        try {
            rentalService.createRental(
                    selectedCar,
                    new Customer(customerId, name),
                    startStr,
                    endStr
            );
            System.out.println("Rental successful for Customer ID: " + customerId);
        } catch (CarNotAvailableException e) {
            System.out.println("Booking Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("System Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void completeRental() {
        List<Rental> allRentals = rentalRepo.findAll();
        if (allRentals.isEmpty()) {
            System.out.println("No active rentals found.");
            return;
        }
        allRentals.forEach(System.out::println);
        System.out.print("Enter rental ID to complete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ((RentalRepositoryImpl) rentalRepo).updateStatus(id, "COMPLETED");

        Car car = carRepo.findById(id);
        if (car != null) {
            car.setAvailable(true);
            System.out.println("Rental completed!");
        }
    }
}