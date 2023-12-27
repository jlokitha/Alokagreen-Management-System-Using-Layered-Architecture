package lk.lokitha.alokagreen.bo.custom;

import com.google.zxing.WriterException;
import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.EmployeeDto;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    boolean saveEmployee(EmployeeDto dto) throws SQLException, IOException, WriterException;
    boolean deleteEmployee(String id) throws SQLException;
    boolean updateEmployee(EmployeeDto dto) throws SQLException;
    EmployeeDto getEmployeeData(String id) throws SQLException;
    ArrayList<String> getAllId() throws SQLException;
    ArrayList<String> getEmployeeRoles();
    String calculateWorkingDays(LocalDate startDate);
}
