package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.UserDto;

import java.sql.SQLException;

public interface SignUpBO extends SuperBO {
    String getEmployeeName(String id) throws SQLException;
    String getUserName(String user) throws SQLException;
    void sendEmail ( String... data);
    String getEmployeeId(String user) throws SQLException;
    boolean saveUser( UserDto dto ) throws SQLException;
}
