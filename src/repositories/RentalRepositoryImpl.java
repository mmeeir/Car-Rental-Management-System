package repositories;

import edu.aitu.db.DataBaseConnection;
import models.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class RentalRepositoryImpl implements RentalRepository {
    @Override
    public void saveRental(int carId, int customerId, String start, String end, double totalCost) {
        String sql = "INSERT INTO rentals (car_id, customer_id, start_date, end_date, total_cost, status) VALUES (?, ?, ?, ?, ?, 'ACTIVE')";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, carId);
            pstmt.setInt(2, customerId);
            pstmt.setDate(3, Date.valueOf(start));
            pstmt.setDate(4, Date.valueOf(end));
            pstmt.setDouble(5, totalCost);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    @Override
    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                rentals.add(new Rental(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getInt("customer_id"),
                        rs.getString("start_date"),
                        rs.getString("end_date"),
                        rs.getDouble("total_cost")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }
    @Override
    public void updateStatus(int id, String status) {
        String sql = "UPDATE rentals SET status = ? WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
