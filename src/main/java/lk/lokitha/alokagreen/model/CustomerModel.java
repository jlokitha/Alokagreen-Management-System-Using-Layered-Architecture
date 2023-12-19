package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerModel {

    public static boolean saveCustomer(final CustomerDto customerDto) {

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, customerDto.getCustomer_Id());
            ps.setString(2, customerDto.getName());
            ps.setString(3, customerDto.getMobile());
            ps.setString(4, customerDto.getEmail());
            ps.setString(5, customerDto.getAddress());
            ps.setString(6, customerDto.getDate());
            ps.setString(7, customerDto.getTime());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static int getLastId() {

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(customer_Id, 3) AS SIGNED)) AS max_value FROM customer";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static ArrayList<String> getAllId() {

        ArrayList<String> ids = new ArrayList<>();

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT customer_Id FROM customer ORDER BY CAST(SUBSTRING(customer_Id, 3) AS UNSIGNED)";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ids;
    }

    public static ArrayList<String> getAllMobile() {

        ArrayList<String> ids = new ArrayList<>();

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT mobile FROM customer ORDER BY CAST(SUBSTRING(customer_Id, 3) AS UNSIGNED)";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ids;
    }

    public static String getNameOfId(final String id) {

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT name FROM customer WHERE customer_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static String getNameOfMobile(final String mobile) {

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT name FROM customer WHERE mobile = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, mobile);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static String getIdOfMobile(final String mobile) {

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT customer_Id FROM customer WHERE mobile = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, mobile);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static CustomerDto getData(final String id) {
        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT * FROM customer WHERE customer_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            CustomerDto cDto = null;

            while (resultSet.next()) {
                cDto = new CustomerDto();

                cDto.setCustomer_Id(resultSet.getString(1));
                cDto.setName(resultSet.getString(2));
                cDto.setMobile(resultSet.getString(3));
                cDto.setEmail(resultSet.getString(4));
                cDto.setAddress(resultSet.getString(5));
                cDto.setDate(resultSet.getString(6));
                cDto.setTime(resultSet.getString(7));
            }

            return cDto;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean updateCustomer(final CustomerDto customerDto) {
        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "UPDATE customer SET name=?, mobile=?, email=?, address=? WHERE customer_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, customerDto.getName());
            ps.setString(2, customerDto.getMobile());
            ps.setString(3, customerDto.getEmail());
            ps.setString(4, customerDto.getAddress());
            ps.setString(5, customerDto.getCustomer_Id());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static boolean deleteCustomer(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM customer WHERE customer_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
