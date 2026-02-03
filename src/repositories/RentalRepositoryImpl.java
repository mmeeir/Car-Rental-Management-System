package repositories;


import edu.aitu.db.DatabaseConnection;
import edu.exceptions.DatabaseException;
import edu.exceptions.RentalOverlapException;
import models.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public  class RentalRepositoryImpl implements Repository<Rental> {

    public boolean isCarOccupied(int carId, String start, String end) {
        String sql = "SELECT COUNT(*) FROM rentals WHERE car_id = ? AND status = 'ACTIVE' " +
                "AND NOT (end_date <= ? OR start_date >= ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, carId);
            pstmt.setDate(2, java.sql.Date.valueOf(start));
            pstmt.setDate(3, java.sql.Date.valueOf(end));

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }
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
        } catch (SQLException e) {
            throw new DatabaseException("Failed to add rental", e);
        }
    }

    @Override
    public List<Rental> findAll() {
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
    public Rental findById(int id) {
        String sql = "SELECT * FROM rentals WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Rental(
                        rs.getInt("id"),
                        rs.getInt("car_id"),
                        rs.getInt("customer_id"),
                        rs.getDate("start_date").toLocalDate(),
                        rs.getDate("end_date").toLocalDate(),
                        rs.getDouble("total_cost"),
                        rs.getString("status")
                );
            }

        } catch (Exception e) {
            throw new DatabaseException("Failed to find rental", e);
        }

        return null;
    }

    public void addRental(Rental rental) throws RentalOverlapException {

        if (isCarOccupied(rental.getCarId(), rental.getStartDate().toString(), rental.getEndDate().toString())) {
            throw new RentalOverlapException("Car is already in rental");
        }


    }



}
