package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.EmployeeDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeModel {

    public static boolean saveEmployee(final EmployeeDto employeeDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, employeeDto.getEmployee_Id());
            ps.setString(2, employeeDto.getFirst_Name());
            ps.setString(3, employeeDto.getLast_Name());
            ps.setString(4, employeeDto.getNic());
            ps.setString(5, employeeDto.getHouse_No());
            ps.setString(6, employeeDto.getStreet());
            ps.setString(7, employeeDto.getCity());
            ps.setString(8, employeeDto.getMobile());
            ps.setString(9, employeeDto.getEmail());
            ps.setString(10, employeeDto.getRole());
            ps.setString(11, employeeDto.getDate());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {return true;}

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static int getLastId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(employee_Id, 3) AS SIGNED)) AS max_value FROM employee";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet id = ps.executeQuery();

            if (id.next()) {
                return id.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static String getNameOfId(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT first_Name, last_Name FROM employee WHERE employee_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet name = ps.executeQuery();

            if (name.next()) {
                return (name.getString(1) + " " + name.getString(2));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT employee_Id FROM employee ORDER BY CAST(SUBSTRING(employee_Id, 3) AS UNSIGNED)";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            ArrayList<String> ids = new ArrayList<>();

            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }

            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateEmployee(final EmployeeDto employeeDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE employee SET first_Name=?, last_Name=?, nic=?, house_No=?, street=?, city=?, mobile=?, email=?, role=? WHERE employee_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, employeeDto.getFirst_Name());
            ps.setString(2, employeeDto.getLast_Name());
            ps.setString(3, employeeDto.getNic());
            ps.setString(4, employeeDto.getHouse_No());
            ps.setString(5, employeeDto.getStreet());
            ps.setString(6, employeeDto.getCity());
            ps.setString(7, employeeDto.getMobile());
            ps.setString(8, employeeDto.getEmail());
            ps.setString(9, employeeDto.getRole());
            ps.setString(10, employeeDto.getEmployee_Id());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {return true;}

        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static EmployeeDto getDetail(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM employee WHERE employee_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            EmployeeDto eDto = new EmployeeDto();

            while (resultSet.next()) {
                eDto.setEmployee_Id(resultSet.getString(1));
                eDto.setFirst_Name(resultSet.getString(2));
                eDto.setLast_Name(resultSet.getString(3));
                eDto.setNic(resultSet.getString(4));
                eDto.setHouse_No(resultSet.getString(5));
                eDto.setStreet(resultSet.getString(6));
                eDto.setCity(resultSet.getString(7));
                eDto.setMobile(resultSet.getString(8));
                eDto.setEmail(resultSet.getString(9));
                eDto.setRole(resultSet.getString(10));
                eDto.setDate(resultSet.getString(11));
            }

            return eDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteEmployee(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM employee WHERE employee_Id = ?";

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

    public static String getEmailOfId(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT email FROM employee WHERE employee_Id = ?";

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

}
