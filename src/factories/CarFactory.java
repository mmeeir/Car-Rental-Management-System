package factories;

import models.Car;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarFactory {

    public static Car fromResultSet(ResultSet rs) throws SQLException {
        return new Car(
                rs.getInt("id"),
                rs.getString("model"),
                rs.getDouble("price_per_day")
        );
    }
}
