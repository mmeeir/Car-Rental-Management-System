package models;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Rental {
    private int id;
    private int carId;
    private int customerId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status;


    public Rental(Builder builder) {
        this.id = builder.id;
        this.carId = builder.carId;
        this.customerId = builder.customerId;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.totalCost = builder.totalCost;
        this.status = builder.status;
    }

    public Rental(int id, int carId, int customerId, LocalDate startDate, LocalDate endDate, double totalCost, String status) {
    }

    public Rental(int carId, int customerId, Date sqlStart, Date sqlEnd) {
        this.carId = carId;
        this.customerId = customerId;

        this.startDate = sqlStart.toLocalDate();
        this.endDate = sqlEnd.toLocalDate();
    }


    public static class Builder {
        private int id;
        private int carId;
        private int customerId;
        private LocalDate startDate;
        private LocalDate endDate;
        private double totalCost;
        private String status = "ACTIVE";

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setCarId(int carId) { this.carId = carId; return this; }
        public Builder setCustomerId(int customerId) { this.customerId = customerId; return this; }
        public Builder setStartDate(LocalDate startDate) { this.startDate = startDate; return this; }
        public Builder setEndDate(LocalDate endDate) { this.endDate = endDate; return this; }
        public Builder setTotalCost(double totalCost) { this.totalCost = totalCost; return this; }
        public Builder setStatus(String status) { this.status = status; return this; }

        public Rental build() {

            return new Rental(this);
        }
    }


    public int getId() { return id; }
    public int getCarId() { return carId; }
    public int getCustomerId() { return customerId; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getTotalCost() { return totalCost; }
    public String getStatus() { return status; }
}
