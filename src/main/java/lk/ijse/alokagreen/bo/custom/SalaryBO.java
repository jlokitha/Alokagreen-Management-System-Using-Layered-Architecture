package lk.ijse.alokagreen.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.EmployeeSalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    boolean saveSalary(EmployeeSalaryDto dto) throws SQLException;
    String employeeWorkedDayCount(String id) throws SQLException;
    String getEmployeeName(String id) throws SQLException;
    String calculateTotalSalary(double baseSalary, double bonus);
    ArrayList<String> getAllEmployeeId() throws SQLException;
    ArrayList<String> getSalaryForMonth(String yearMonth) throws SQLException;
    ArrayList<String> getAllSalaryIds() throws SQLException;
    ObservableList<String> setYears();
    String mapMonthToNumber(String month);
    boolean deleteSalary(String id) throws SQLException;
    EmployeeSalaryDto getSalaryData(String id) throws SQLException;
    boolean updateSalary(EmployeeSalaryDto dto) throws SQLException;
}
