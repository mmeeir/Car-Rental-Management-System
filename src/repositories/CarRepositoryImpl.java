package repositories;


import edu.aitu.db.DatabaseConnection;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements Repository<Car> {
    @Override
    public void add(Car car) {

        String sql = "INSERT INTO cars (model, price_per_day, is_available) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, car.getModel());
            pstmt.setDouble(2, car.getPricePerDay());
            pstmt.setBoolean(3, car.isAvailable());
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    public List<Car> getAll() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE is_available = true";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("model"), rs.getDouble("price_per_day"), rs.getBoolean("is_available")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return cars;
    }


    public void updateAvailability(int carId, boolean available) {
        String sql = "UPDATE cars SET is_available = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, available);
            pstmt.setInt(2, carId);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Car getById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Car(rs.getInt("id"), rs.getString("model"), rs.getDouble("price_per_day"), rs.getBoolean("is_available"));
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }


}
