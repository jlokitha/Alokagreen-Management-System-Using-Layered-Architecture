package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.User;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface UserDAO extends CrudUtil<User> {
    boolean checkPassword(final String userName, final String password) throws SQLException;

    boolean updatePassword(final String userName, final String password) throws SQLException;

    String getEmployeeId(final String user) throws SQLException;

    String getUserName(final String eId) throws SQLException;
}
