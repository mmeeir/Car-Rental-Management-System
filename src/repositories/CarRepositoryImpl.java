package repositories;

import edu.aitu.db.DataBaseConnection;
import models.Car;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarRepositoryImpl implements CarRepository {
    public List<Car> getAllAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE is_available = true";
        try (Connection conn = DataBaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                cars.add(new Car(rs.getInt("id"), rs.getString("model"), rs.getDouble("price_per_day"), rs.getBoolean("is_available")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return cars;
    }

    @Override
    public void updateAvailability(int carId, boolean available) {
        String sql = "UPDATE cars SET is_available = ? WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setBoolean(1, available);
            pstmt.setInt(2, carId);
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public Car getCarById(int id) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        try (Connection conn = DataBaseConnection.getConnection();
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
