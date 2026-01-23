
import java.time.LocalDate;
public class Rental {
    private final Car selectedCar;
    private int id;
    private int carId;
    private int customerId;
    private int paymentId;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCost;
    private String status;
    private String customerName;


    public Rental(int carId, int customerId, LocalDate startDate, LocalDate endDate, double totalCost) {
        this.carId = carId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = "ACTIVE";
    }


    public Rental(int id, int carId, int customerId, int paymentId, LocalDate startDate, LocalDate endDate, double totalCost, String status) {
        this.id = id;
        this.carId = carId;
        this.customerId = customerId;
        this.paymentId = paymentId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.status = status;
    }

    public Rental(int i, Car selectedCar, String customerName) {
        this.customerName = customerName;
        this.selectedCar = selectedCar;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCarId() { return carId; }
    public void setCarId(int carId) { this.carId = carId; }
    public int getCustomerId() { return customerId; }
    public void setCustomerId(int customerId) { this.customerId = customerId; }
    public int getPaymentId() { return paymentId; }
    public void setPaymentId(int paymentId) { this.paymentId = paymentId; }
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }
    public double getTotalCost() { return totalCost; }
    public void setTotalCost(double totalCost) { this.totalCost = totalCost; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}