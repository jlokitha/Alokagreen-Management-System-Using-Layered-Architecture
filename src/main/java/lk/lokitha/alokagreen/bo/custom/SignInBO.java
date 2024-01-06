package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;

import java.sql.SQLException;

public interface SignInBO extends SuperBO {
    boolean checkPassword(String user, String password) throws SQLException;
    String getEmployeeId(String user) throws SQLException;
    String getEmployeeEmail(String id) throws SQLException;
    void sendEmail ( String... data);
    boolean updateUserPassword(String user, String password) throws SQLException;
    String getEmployeeName(String id) throws SQLException;
    String getUserName(String user) throws SQLException;
}