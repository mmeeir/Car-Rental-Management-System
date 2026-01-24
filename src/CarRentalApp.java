import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalApp {

    private Scanner scanner = new Scanner(System.in);
    private List<Car> cars = new ArrayList<>();
    private List<Rental> rentals = new ArrayList<>();

    public CarRentalApp() {
        cars.add(new Car(1, "Toyota Camry", 50));
        cars.add(new Car(2, "Honda Civic", 45));
        cars.add(new Car(3, "BMW M5", 120));
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
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    private void showRentals() {
        if (rentals.isEmpty()) {
            System.out.println("No rentals yet.");
            return;
        }
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
    }

    private void createRental() {
        System.out.print("Customer name: ");
        Customer customer = new Customer(scanner.nextLine());

        showCars();
        System.out.print("Choose car ID: ");
        int carId = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = cars.stream()
                .filter(c -> c.getId() == carId && c.isAvailable())
                .findFirst()
                .orElse(null);

        if (selectedCar == null) {
            System.out.println("Car not available.");
            return;
        }

        System.out.print("Start date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(scanner.nextLine());

        System.out.print("End date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(scanner.nextLine());

        selectedCar.setAvailable(false);
        Rental rental = new Rental(selectedCar, customer, start, end);
        rentals.add(rental);

        System.out.println("Rental created successfully!");
        System.out.println(rental);
    }

    private void completeRental() {
        showRentals();
        System.out.print("Enter rental ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Rental rental : rentals) {
            if (rental.getId() == id) {
                rental.completeRental();
                System.out.println("Rental completed.");
                return;
            }
        }

        System.out.println("Rental not found.");
    }

    public static void main(String[] args) {
        new CarRentalApp().run();
    }
}
