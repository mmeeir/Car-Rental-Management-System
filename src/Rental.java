public class Rental {
    private int id;
    private int idGen;
    private int vehicleId;
    private int paymentId;
    private int customerId;
    private Car selectedCar;
    private String customerName;
    public Rental(int id, Car selectedCar, String customerName) {
        this.id = idGen++;
        this.selectedCar = selectedCar;
        this.customerName = customerName;
    }
    public Rental(int vehicleId, int paymentId, int customerId) {
        this.vehicleId = vehicleId;
        this.paymentId = paymentId;
        this.customerId = customerId;
    }
    public int getVehicleId() {
        return vehicleId;
    }
    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }
    public int getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public Car getSelectedCar() {
        return selectedCar;
    }
    public void setSelectedCar(Car selectedCar) {
        this.selectedCar = selectedCar;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
}
