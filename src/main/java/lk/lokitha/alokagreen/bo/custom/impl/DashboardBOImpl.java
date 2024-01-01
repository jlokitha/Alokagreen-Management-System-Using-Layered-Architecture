package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.DashboardBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.*;
import lk.lokitha.alokagreen.dao.custom.impl.*;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.entity.Attendance;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DashboardBOImpl implements DashboardBO {
    private final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.EMPLOYEE );
    private final EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.ATTENDANCE );
    private final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.PRODUCT );
    private final ProductStockDAO productStockDAO = (ProductStockDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.PRODUCT_STOCK );
    private final MaterialDAO materialDAO = (MaterialDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.MATERIAL );
    private final MaterialStockDAO materialStockDAO = (MaterialStockDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.MATERIAL_STOCK );
    private final CustomerOrderDAO customerOrderDAO = (CustomerOrderDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.CUSTOMER_ORDER );
    private final SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SUPPLIER_ORDER );
    private final EmployeeSalaryDAO employeeSalaryDAO = (EmployeeSalaryDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SALARY );

    @Override
    public String getEmployeeName(String id) throws SQLException {
        return employeeDAO.getNameOfId( id );
    }

    @Override
    public boolean saveEmployeeAttendance(EmployeeAttendanceDto dto) throws SQLException {
        return employeeAttendanceDAO.save( new Attendance(
                NewId.newAttendanceId(),
                dto.getEmployee_Id(),
                DateTime.dateNow(),
                DateTime.timeNow()
        ) );
    }

    @Override
    public ArrayList<String> getAllProductIds() throws SQLException {
        return productDAO.getAllId();
    }

    @Override
    public Map<String, Integer> setProductDetails() throws SQLException {
        ArrayList<String> allId = productDAO.getAllId();

        Map<String, Integer> pStock = new HashMap<>();

        for (int i = 0; i < allId.size(); i++) {
            String desc = productDAO.getDescOfId(allId.get(i));
            int qty = productStockDAO.getProductQty(allId.get(i));

            if (qty != 0) {
                pStock.put(desc, pStock.getOrDefault(desc, 0) + qty);
            }
        }

        return pStock;
    }

    @Override
    public Map<String, Integer> setMaterialDetails() throws SQLException {
        ArrayList<String> allMaterialId = materialDAO.getAllId();

        Map<String, Integer> mStock = new HashMap<>();

        for (int i = 0; i < allMaterialId.size(); i++) {
            String desc = materialDAO.getDescOfId(allMaterialId.get(i));
            int qty = materialStockDAO.getProductQty(allMaterialId.get(i));

            if ( qty != 0 ) {
                mStock.put(desc, mStock.getOrDefault(desc, 0) + qty);
            }
        }

        return mStock;
    }

    @Override
    public ArrayList<String> getAllCustomerOrderIds() throws SQLException {
        return customerOrderDAO.getAllId();
    }

    @Override
    public double getTotalIncome() throws SQLException {
        return customerOrderDAO.getTotalIncome( );
    }

    @Override
    public double getTotalExpenses() throws SQLException {
        double totalSalary = employeeSalaryDAO.getTotalSalary( );
        double totalExpense = supplierOrderDAO.getTotalExpense( );

        return (totalExpense + totalSalary);
    }

    @Override
    public ArrayList<String> getAllEmployeeIds() throws SQLException {
        return employeeDAO.getAllId();
    }
}
