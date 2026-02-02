package repositories;


import edu.aitu.db.DatabaseConnection;
import models.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class RentalRepositoryImpl implements Repository<Rental> {
    @Override
    public void add(Rental rental) {
        String sql = "INSERT INTO rentals (car_id, customer_id, start_date, end_date, total_cost, status) VALUES (?, ?, ?, ?, ?, 'ACTIVE')";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, rental.getCarId());
            pstmt.setInt(2, rental.getCustomerId());
            pstmt.setDate(3, Date.valueOf(rental.getStartDate()));
            pstmt.setDate(4, Date.valueOf(rental.getEndDate()));
            pstmt.setDouble(5, rental.getTotalCost());
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    System.out.println("Rental ID generated: " + rs.getInt(1));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<Rental> getAll() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                rentals.add(new Rental.Builder()
                        .setId(rs.getInt("id"))
                        .setCarId(rs.getInt("car_id"))
                        .setCustomerId(rs.getInt("customer_id"))
                        .setStartDate(rs.getDate("start_date").toLocalDate())
                        .setEndDate(rs.getDate("end_date").toLocalDate())
                        .setTotalCost(rs.getDouble("total_cost"))
                        .setStatus(rs.getString("status"))
                        .build());
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return rentals;
    }
    public void updateStatus(int id, String status) {
        String sql = "UPDATE rentals SET status = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, status);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    @Override
    public Rental getById(int id) {
        String sql = "SELECT * FROM rentals WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Rental.Builder()
                            .setId(rs.getInt("id"))
                            .setCarId(rs.getInt("car_id"))
                            .setCustomerId(rs.getInt("customer_id"))
                            .setStartDate(rs.getDate("start_date").toLocalDate())
                            .setEndDate(rs.getDate("end_date").toLocalDate())
                            .setTotalCost(rs.getDouble("total_cost"))
                            .setStatus(rs.getString("status"))
                            .build();
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }
}
