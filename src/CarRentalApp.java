import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class CarRentalApp {
    private Scanner scanner = new Scanner(System.in);
    private List<Rental> rents = new ArrayList<>();
    private List<Car> cars = new ArrayList<>();
    public CarRentalApp() {
        cars.add(new Car("Toyota Camry", 1, 50.0));
        cars.add(new Car("Honda Civic", 2, 45.0));
        cars.add(new Car("BMW M5", 3, 120.0));
    }
    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("Welcome to Car rent App!");
            System.out.println("1. Print all cars");
            System.out.println("2. Print all rentals");
            System.out.println("3. Add position to order");
            System.out.println("4. Edit position to order");
            System.out.println("5. Delete position from order");
            System.out.println("6. Finish the order");
            System.out.println("7. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1 -> printAllCars();
                case 2 -> printAllRents();
                case 3 -> addRentItem();
                case 4 -> editRentItem();
                case 5 -> deleteRent();
                case 6 -> finishRent();
                case 7 -> running = false;
                default -> System.out.println("Invalid option");
            }
        }

    }
    public void addRentItem(){
        printAllCars();
        System.out.print("Enter car ID to rent: ");
        int carId = scanner.nextInt();
        scanner.nextLine();
        Car selectedCar = null;
        for (Car car : cars) {
            if (car.getId() == carId) {
                selectedCar = car;
                break;
            }
        }
        if (selectedCar == null) {
            System.out.println("Car not found!");
            return;
        }
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        Rental newRental = new Rental(rents.size() + 1, selectedCar, customerName);
        rents.add(newRental);
        System.out.println("Rental added successfully!");
    }
    public int finishRent(){

        return 0;
    }
    public void deleteRent() {

    }
    public void editRentItem() {

    }
    public void printAllCars() {
        if (cars.isEmpty()){
            System.out.println("No cars in database");
            return;
        }
        for(Car car: cars){
            System.out.println(car);
        }
    }
    public void printAllRents(){
        if (rents.isEmpty()){
            System.out.println("No rent in database");
            return;
        }
        for(Rental rent: rents){
            System.out.println(rent);
        }
    }

}
