package repositories;
import models.Rental;
import java.util.List;
public interface RentalRepository {
    void saveRental(int carId, int customerId, String start, String end, double totalCost);
    List<Rental> getAllRentals();
    void updateStatus(int id, String status);
}
