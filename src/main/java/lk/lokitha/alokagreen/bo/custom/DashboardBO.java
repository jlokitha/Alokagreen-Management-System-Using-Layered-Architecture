package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface DashboardBO extends SuperBO {
    String getEmployeeName(String id) throws SQLException;
    boolean saveEmployeeAttendance(EmployeeAttendanceDto dto) throws SQLException;
    ArrayList<String> getAllProductIds() throws SQLException;
    Map<String, Integer> setProductDetails() throws SQLException;
    Map<String, Integer> setMaterialDetails() throws SQLException;
    ArrayList<String> getAllCustomerOrderIds() throws SQLException;
    double getTotalIncome() throws SQLException;
    double getTotalExpenses() throws SQLException;
    ArrayList<String> getAllEmployeeIds() throws SQLException;
}
