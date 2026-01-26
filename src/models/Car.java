package models;

public class Car {

    private int id;
    private String model;
    private double pricePerDay;
    private boolean isAvailable;

    public Car(int id, String model, double pricePerDay, boolean isAvailable) {
        this.id = id;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        this.isAvailable = available;
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Not Available";
        return id + ". " + model + " - $" + pricePerDay + "/day (" + status + ")";
    }
}
