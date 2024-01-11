package lk.ijse.alokagreen.bo.custom;

import com.google.zxing.WriterException;
import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.EmployeeDto;

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
    String calculateWorkingDays(LocalDate startDate);
}
