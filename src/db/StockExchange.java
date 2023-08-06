package db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StockExchange {
    private static String dbUrl = "jdbc:mysql://localhost:3306/mydbtest";
    private static String dbUsername = "root";
    private static String dbPassword = "root";
    public static Connection getConnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Could not establish connection with database");
            throw new RuntimeException(e);
        }
        return conn;
    }
}
