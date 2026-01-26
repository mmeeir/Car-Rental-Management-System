package repositories;

import edu.aitu.db.DataBaseConnection;
import java.sql.*;

public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public int createCustomerAndGetId(String name) {
        String sql = "INSERT INTO customers (name) VALUES (?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name);
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}