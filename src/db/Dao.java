package db;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class Dao {
    private static final String INSERT_TS = "insert into transport_machines (make, model, category, registration_number, machine_type, year, trailer) values (?,?,?,?,?,?,?)";
    private static final String UPDATE_TS = "update transport_machines set make = ?, model = ?, category = ?, registration_number = ?, machine_type = ?, year = ?, trailer = ? where id = ?";
    public static Vector<TransportMachine> get(String query) {
        Vector<TransportMachine> ts_machines = new Vector<>();

        try (Connection conn = StockExchange.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(query)
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ts_machines.add(new TransportMachine(
                        resultSet.getInt("id"),
                        resultSet.getString("make"),
                        resultSet.getString("model"),
                        resultSet.getString("category"),
                        resultSet.getString("registration_number"),
                        resultSet.getString("machine_type"),
                        resultSet.getInt("year"),
                        resultSet.getBoolean("trailer")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to get entries from database");
            throw new RuntimeException(e);
        }
        return ts_machines;
    }

    public static void create(TransportMachine transportMachine) {
        try (Connection conn = StockExchange.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("select * from transport_machines where registration_number='" + transportMachine.getRegistration_number() + "';")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Table entry with supplied registration number already exists!");
                return;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to get entry from database");
            throw new RuntimeException(e);
        }

        try (Connection conn = StockExchange.getConnection();
             PreparedStatement preparedStatement =
                     conn.prepareStatement(INSERT_TS)
        ) {
            preparedStatement.setString(1, transportMachine.getMake());
            preparedStatement.setString(2, transportMachine.getModel());
            preparedStatement.setString(3, transportMachine.getCategory());
            preparedStatement.setString(4, transportMachine.getRegistration_number());
            preparedStatement.setString(5, transportMachine.getMachine_type());
            preparedStatement.setInt(6, transportMachine.getYear());
            preparedStatement.setBoolean(7, transportMachine.hasTrailer());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to insert entry from database");
            throw new RuntimeException(e);
        }
    }

    public static void update(TransportMachine transportMachine){
        try (Connection conn = StockExchange.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement("select * from transport_machines where id=" + transportMachine.getId() + ";")
        ) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Table entry with supplied id was not found!");
                return;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = StockExchange.getConnection();
             PreparedStatement preparedStatement =
                     conn.prepareStatement(UPDATE_TS)
        ) {
            preparedStatement.setString(1, transportMachine.getMake());
            preparedStatement.setString(2, transportMachine.getModel());
            preparedStatement.setString(3, transportMachine.getCategory());
            preparedStatement.setString(4, transportMachine.getRegistration_number());
            preparedStatement.setString(5, transportMachine.getMachine_type());
            preparedStatement.setInt(6, transportMachine.getYear());
            preparedStatement.setBoolean(7, transportMachine.hasTrailer());
            preparedStatement.setInt(8, transportMachine.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to update entry from database");
            throw new RuntimeException(e);
        }
    }
}
