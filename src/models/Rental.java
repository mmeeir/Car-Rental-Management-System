package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {

    private static int idCounter = 1;

    private int id;
    private Car car;
    private int carId;
    private int customerId;
    private Customer customer;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status; // ACTIVE / COMPLETED

    public Rental(Car car, Customer customer, LocalDate startDate, LocalDate endDate) {
        this.car = car;
        this.customer = customer;
        this.carId = car.getId();
        this.customerId = customer.getId();
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = calculateTotalCost();
        this.status = "ACTIVE";
    }

    public Rental(int id, int carId, int customerId, String startDate, String endDate, double totalCost) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = (startDate != null) ? java.time.LocalDate.parse(startDate) : null;
        this.endDate = (endDate != null) ? java.time.LocalDate.parse(endDate) : null;
        this.totalCost = totalCost;
        this.status = "ACTIVE";
    }

    private double calculateTotalCost() {
        if (startDate == null || endDate == null || car == null) {
            return this.totalCost;
        }
        long days = ChronoUnit.DAYS.between(startDate, endDate);
        if (days <= 0) days = 1;
        return days * car.getPricePerDay();
    }

    public void completeRental() {
        this.status = "COMPLETED";
        if (this.car != null) {
            this.car.setAvailable(true);
        }
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

    public int getCarId() {return carId;}

    @Override
    public String toString() {
        String customerName = (customer != null) ? customer.getName() : "ID: " + customerId;
        String carModel = (car != null) ? car.getModel() : "ID: " + carId;
        return "models.Rental ID: " + id +
                "\nmodels.Customer: " + customerName +
                "\nmodels.Car: " + carModel +
                "\nPeriod: " + startDate + " -> " + endDate +
                "\nTotal cost: $" + totalCost +
                "\nStatus: " + status +
                "\n--------------------------";
    }
}
