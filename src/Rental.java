import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {

    private static int idCounter = 1;

    private int id;
    private Car car;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status; // ACTIVE / COMPLETED

    public Rental(Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.id = idCounter++;
        this.car = car;
        this.customer = customer;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotalCost();
        this.status = "ACTIVE";
    }

    private double calculateTotalCost() {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) days = 1;
        return days * car.getPricePerDay();
    }

    public void completeRental() {
        status = "COMPLETED";
        car.setAvailable(true);
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Rental ID: " + id +
                "\nCustomer: " + customer.getName() +
                "\nCar: " + car.getModel() +
                "\nPeriod: " + startDate + " → " + endDate +
                "\nTotal cost: $" + totalCost +
                "\nStatus: " + status +
                "\n---------------------------";
    }
}
