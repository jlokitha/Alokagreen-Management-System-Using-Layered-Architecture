package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.CustomerOrderDto;
import lk.ijse.alokagreen.dto.EmployeeAttendanceDto;
import lk.ijse.alokagreen.dto.MaterialStockDto;

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
    String getCustomerName(String id) throws SQLException;
    String getSupplierName(String id) throws SQLException;
    String getProductIdOfStock(String id) throws SQLException;
    MaterialStockDto getMaterialStockDetails( String id) throws SQLException;
    CustomerOrderDto getCustomerOrderDetails( String id) throws SQLException;
    String getSupplierIdOfSupilerOrder(String id) throws SQLException;
    String getCustomerIdOfMobile(String mobile) throws SQLException;
    String getUserEmpId(String user) throws SQLException;
}
