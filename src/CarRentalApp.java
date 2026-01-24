import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CarRentalApp {
    private Scanner scanner = new Scanner(System.in);
    private List<Rental> rents = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();

    public CarRentalApp() {
        cars.add(new Car(1, "Toyota Camry", 50.0));
        cars.add(new Car(2, "Honda Civic", 45.0));
        cars.add(new Car(3, "BMW M5", 120.0));
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("\n=== CAR RENTAL SYSTEM ===");
            System.out.println("1. View cars");
            System.out.println("2. View rentals");
            System.out.println("3. Create rental");
            System.out.println("4. Complete rental");
            System.out.println("5. Delete rental");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> printAllCars();
                case 2 -> printAllRents();
                case 3 -> addRentItem();
                case 4 -> finishRent();
                case 5 -> deleteRent();
                case 0 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    private void printAllCars() {
        System.out.println("\nAvailable cars:");
        for (Car car : cars) {
            System.out.println(car.getId() + ". " +
                    car.getModel() + " - $" +
                    car.getPricePerDay() + "/day");
        }
    }

    private void printAllRents() {
        if (rents.isEmpty()) {
            System.out.println("No rentals yet.");
            return;
        }

        for (Rental rent : rents) {
            System.out.println(rent);
        }
    }

    private void addRentItem() {
        System.out.print("Customer name: ");
        String name = scanner.nextLine();

        printAllCars();
        System.out.print("Choose car ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Car selectedCar = cars.stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElse(null);

        if (selectedCar == null) {
            System.out.println("Invalid car ID.");
            return;
        }

        System.out.print("Start date (YYYY-MM-DD): ");
        LocalDate start = LocalDate.parse(scanner.nextLine());

        System.out.print("End date (YYYY-MM-DD): ");
        LocalDate end = LocalDate.parse(scanner.nextLine());

        Rental rental = new Rental(selectedCar, name, start, end);
        rents.add(rental);

        System.out.println("Rental created successfully!");
        System.out.println(rental);
    }

    private void finishRent() {
        printAllRents();
        System.out.print("Enter rental ID to complete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Rental rent : rents) {
            if (rent.getId() == id) {
                rent.completeRental();
                System.out.println("Rental completed.");
                return;
            }
        }

        System.out.println("Rental not found.");
    }

    private void deleteRent() {
        printAllRents();
        System.out.print("Enter rental ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        rents.removeIf(r -> r.getId() == id);
        System.out.println("Rental deleted.");
    }
    public static void main(String[] args) {
        CarRentalApp app = new CarRentalApp();
        app.run();
    }
}