package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeDAO {
    public boolean saveEmployee(final EmployeeDto dto) throws SQLException;
    boolean updateEmployee(final EmployeeDto dto) throws SQLException;
    boolean deleteEmployee(final String id) throws SQLException;
    int getLastId() throws SQLException;
    ArrayList<String> getAllId() throws SQLException;
    EmployeeDto getDetail(final String id) throws SQLException;
    String getNameOfId(final String id) throws SQLException;
    String getEmailOfId(final String id) throws SQLException;
}
