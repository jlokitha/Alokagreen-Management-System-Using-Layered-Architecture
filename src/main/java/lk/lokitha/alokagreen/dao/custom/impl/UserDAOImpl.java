package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.UserDAO;
import lk.lokitha.alokagreen.entity.User;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public boolean save(User entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO user VALUES(?, ?, ?)",
                entity.getUserName(),
                entity.getPassword(),
                entity.getEmployeeId()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM user WHERE user_Name = ?", id );
    }

    @Override
    public boolean checkPassword(final String userName, final String password) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT password FROM user WHERE user_Name = ?", userName );

        if (rst.next()) {
            if (password .equals(rst.getString(1))){
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean updatePassword(final String userName, final String password) throws SQLException {
        return SQLUtil.execute( "UPDATE user SET password = ? WHERE user_Name = ?",
                password,
                userName
        );
    }

    @Override
    public String getEmployeeId(final String user) throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "SELECT employee_Id FROM user WHERE user_Name = ?", user );

        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }

    @Override
    public String getUserName(final String eId) throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "SELECT user_Name FROM user WHERE employee_Id = ?", eId );

        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }

    @Override
    public boolean update(User entity) throws SQLException {
        return false;
    }

    @Override
    public User getData(String id) throws SQLException {
        return null;
    }

    @Override
    public int getLastId() throws SQLException {
        return 0;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        return null;
    }
}
