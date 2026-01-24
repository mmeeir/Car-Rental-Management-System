import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {

    private static int idCounter = 1;

    private int id;
    private Car car;
    private String customerName;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status; // ACTIVE / COMPLETED

    public Rental(Car car, String customerName, LocalDate startDate, LocalDate endDate) {
        this.id = idCounter++;
        this.car = car;
        this.customerName = customerName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotalCost();
        this.status = "ACTIVE";
    }

    private double calculateTotalCost() {
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) {
            days = 1;
        }
        return days * car.getPricePerDay();
    }

    public void completeRental() {
        this.status = "COMPLETED";
    }

    public int getId() {
        return id;
    }

    public Car getCar() {
        return car;
    }

    public String getCustomerName() {
        return customerName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Rental ID: " + id +
                "\nCustomer: " + customerName +
                "\nCar: " + car.getModel() +
                "\nRental period: " + startDate + " → " + endDate +
                "\nTotal cost: $" + totalCost +
                "\nStatus: " + status +
                "\n-----------------------------";
    }
}