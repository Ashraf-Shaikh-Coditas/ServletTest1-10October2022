package DAO;

import com.mysql.cj.exceptions.DataReadException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public static Connection connect() {
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/assignments","root","root");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return connection;

    }
}
