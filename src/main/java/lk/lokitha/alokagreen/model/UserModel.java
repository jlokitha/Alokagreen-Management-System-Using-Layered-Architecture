package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserModel {
    public static boolean checkPassword(final String userName, final String password) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT password FROM user WHERE user_Name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, userName);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                if (password .equals(resultSet.getString(1))){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static boolean updatePassword(final String userName, final String password) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE user SET password = ? WHERE user_Name = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, password);
            preparedStatement.setString(2, userName);

            int affectedRows = preparedStatement.executeUpdate();

            return affectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getEmployeeId(final String user) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT employee_Id FROM user WHERE user_Name = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, user);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static boolean saveUser(final UserDto userDto) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO user VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, userDto.getUsername());
            ps.setString(2, userDto.getPassword());
            ps.setString(3, userDto.getEmpId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean deleteUser(final String userName) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM user WHERE user_Name = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, userName);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUserName(final String eId) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT user_Name FROM user WHERE employee_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, eId);

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
