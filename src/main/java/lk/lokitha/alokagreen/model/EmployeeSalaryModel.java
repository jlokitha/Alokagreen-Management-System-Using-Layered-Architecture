package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryModel {

    public static boolean saveEmpSalary(final EmployeeSalaryDto employeeSalaryDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO salary VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, employeeSalaryDto.getSalary_Id());
            ps.setString(2, employeeSalaryDto.getEmployee_Id());
            ps.setDouble(3, employeeSalaryDto.getTotal_Salary());
            ps.setInt(4, employeeSalaryDto.getWorked_Days());
            ps.setDouble(5, employeeSalaryDto.getBonus());
            ps.setString(6, employeeSalaryDto.getDate());
            ps.setString(7, employeeSalaryDto.getTime());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT salary_Id FROM salary ORDER BY date DESC, time DESC";

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

    public static EmployeeSalaryDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM salary WHERE salary_Id  = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            EmployeeSalaryDto data = null;

            while (resultSet.next()) {
                data = new EmployeeSalaryDto();

                data.setSalary_Id(resultSet.getString(1));
                data.setEmployee_Id(resultSet.getString(2));
                data.setTotal_Salary(resultSet.getDouble(3));
                data.setWorked_Days(resultSet.getInt(4));
                data.setBonus(resultSet.getDouble(5));
                data.setDate(resultSet.getString(6));
                data.setTime(resultSet.getString(7));
            }

            return data;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getLastId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(salary_Id, 4) AS SIGNED)) AS max_value FROM salary";

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

    public static boolean updateEmpSalary(final EmployeeSalaryDto employeeSalaryDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE salary SET employee_Id=?, total_Salary=?, work_Day_Count=?, bonus=? " +
                            "WHERE salary_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(5, employeeSalaryDto.getSalary_Id());
            ps.setString(1, employeeSalaryDto.getEmployee_Id());
            ps.setDouble(2, employeeSalaryDto.getTotal_Salary());
            ps.setInt(3, employeeSalaryDto.getWorked_Days());
            ps.setDouble(4, employeeSalaryDto.getBonus());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    public static boolean deleteSalary(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM salary WHERE salary_Id = ?";

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

    public static double getTotalSalary() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT SUM(total_Salary) AS total_salary_sum FROM salary";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getDouble(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0.00;
    }

    public static ArrayList<String> getSalaryForMonth(final String date) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT salary_Id FROM salary WHERE date LIKE ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, date);

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
}
