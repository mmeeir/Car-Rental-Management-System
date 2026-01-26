import edu.exceptions.CarNotAvailableException;
import models.Car;
import models.Customer;
import models.Rental;
import repositories.CarRepositoryImpl;
import repositories.CustomerRepositoryImpl;
import repositories.RentalRepositoryImpl;
import services.PricingService;
import services.RentalService;
import repositories.CustomerRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalApp {
    private final CarRepositoryImpl carRepo = new CarRepositoryImpl();
    private final CustomerRepository customerRepo = new CustomerRepositoryImpl();
    private final RentalRepositoryImpl rentalRepo = new RentalRepositoryImpl() ;
    private final PricingService pricingService = new PricingService();
    private final RentalService rentalService = new RentalService(carRepo, rentalRepo, pricingService);
    private Scanner scanner = new Scanner(System.in);
    private List<Car> cars = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public CarRentalApp() {

    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== CAR RENTAL SYSTEM ===");
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
        List<Car> allCars = carRepo.getAllAvailableCars();
        if (allCars.isEmpty()) {
            System.out.println("No cars found in database.");
        } else {
            allCars.forEach(System.out::println);
        }
    }

    private void showRentals() {
        System.out.println("\n--- Current Rentals ---");
        List<Rental> allRentals = rentalRepo.getAllRentals();

        if (allRentals == null || allRentals.isEmpty()) {
            System.out.println("No rentals yet.");
            return;
        }
        allRentals.forEach(System.out::println);
    }

    private void createRental() {
        System.out.print("Customer name: ");
        String name = scanner.nextLine();
        int customerId = customerRepo.createCustomerAndGetId(name);

        if (customerId == -1) {
            System.out.println("Error registering client in the database!");
            return;
        }
        Customer customer = new Customer(customerId, name);

        System.out.println("\nAvailable Cars:");
        carRepo.getAllAvailableCars().forEach(System.out::println);

        System.out.print("Choose car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = carRepo.getCarById(carId);
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
                    customer,
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
        List<Rental> allRentals = rentalRepo.getAllRentals();
        if (allRentals.isEmpty()) {
            System.out.println("No active rentals found.");
            return;
        }
        allRentals.forEach(System.out::println);
        System.out.print("Enter rental ID to complete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Rental selectedRental = null;
        for (Rental r : allRentals) {
            if (r.getId() == id) {
                selectedRental = r;
                break;
            }
        }
        if (selectedRental != null) {
            rentalRepo.updateStatus(id, "COMPLETED");
            carRepo.updateAvailability(selectedRental.getCarId(), true);

            System.out.println("Rental completed successfully! Car is now available.");
        } else {
            System.out.println("Rental with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        new CarRentalApp().run();
    }
}
