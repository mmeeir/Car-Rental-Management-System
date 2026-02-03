package repositories;

import edu.aitu.db.DatabaseConnection;
import edu.exceptions.DatabaseException;
import models.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepositoryImpl implements Repository<Customer> {


    public boolean create(Customer customer) {
        String sql = "INSERT INTO customers (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, customer.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
        return false;
    }


    public int createCustomerAndGetId(String name) {
        String sql = "INSERT INTO customers (name) VALUES (?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate();
            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customers";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                customers.add(new Customer(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return customers;
    }
    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM customers WHERE id = ?";

        try (Connection con = DatabaseConnection.getConnection();
             PreparedStatement st = con.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Customer(
                        rs.getInt("id"),
                        rs.getString("name")
                );
            }

        } catch (Exception e) {
            throw new DatabaseException("Failed to find customer", e);
        }

        return null;
    }


    @Override
    public void add(Customer customer) {
        create(customer);

    }


}