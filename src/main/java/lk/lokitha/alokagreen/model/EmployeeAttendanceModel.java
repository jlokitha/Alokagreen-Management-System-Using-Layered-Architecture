package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceModel {

    public static boolean saveEmployeeAttendance(final EmployeeAttendanceDto employeeAttendanceDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO attendance VALUES(?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, employeeAttendanceDto.getAttendance_Id());
            ps.setString(2, employeeAttendanceDto.getEmployee_Id());
            ps.setString(3, employeeAttendanceDto.getDate());
            ps.setString(4, employeeAttendanceDto.getTime());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {return true;}

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static int getEmployeeWorkDayCount(final String id, final String yearMonth) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT COUNT(*) AS work_days FROM attendance WHERE employee_Id = ?  AND date LIKE ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);
            ps.setString(2, yearMonth + "%");

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0;
    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT attendance_Id FROM attendance ORDER BY CAST(SUBSTRING(attendance_Id, 3) AS UNSIGNED)";

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

    public static EmployeeAttendanceDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM attendance WHERE attendance_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            EmployeeAttendanceDto data = new EmployeeAttendanceDto();

            if (resultSet.next()) {

                data.setAttendance_Id(resultSet.getString(1));
                data.setEmployee_Id(resultSet.getString(2));
                data.setDate(resultSet.getString(3));
                data.setTime(resultSet.getString(4));

            }

            return data;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getLastId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(attendance_Id, 4) AS SIGNED)) AS max_value FROM attendance";

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

    public static boolean updateEmployeeAttendance(final String... data) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE attendance SET employee_Id=? WHERE attendance_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, data[1]);
            ps.setString(2, data[0]);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {return true;}

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static boolean deleteEmployeeAttendance(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM attendance WHERE attendance_Id = ?";

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

    public static ArrayList<String> getAttendanceForDate(final String date) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT attendance_Id FROM attendance WHERE date = ?";

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
