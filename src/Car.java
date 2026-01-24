public class Car {

    private int id;
    private String model;
    private double pricePerDay;
    private boolean available;

    public Car(int id, String model, double pricePerDay) {
        this.id = id;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.available = true;
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
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        return id + ". " + model + " - $" + pricePerDay + "/day"
                + (available ? " (Available)" : " (Rented)");
    }
}
