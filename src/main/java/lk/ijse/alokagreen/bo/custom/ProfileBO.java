package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.EmployeeDto;

import java.sql.SQLException;

public interface ProfileBO extends SuperBO {
    String getEmployeeId(String username) throws SQLException;
    EmployeeDto getEmployeeDetails(String id) throws SQLException;
    boolean checkPassword(String user, String password) throws SQLException;
    String getEmployeeEmail(String id) throws SQLException;
    boolean deleteUser(String user) throws SQLException;
    void sendAccountDeletionEmail(String email);
    boolean updatePassword(String user, String password) throws SQLException;
    void sendPasswordChangeEmail(String email);
}
