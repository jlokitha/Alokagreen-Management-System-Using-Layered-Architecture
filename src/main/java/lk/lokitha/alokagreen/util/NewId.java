package lk.lokitha.alokagreen.util;

import lk.lokitha.alokagreen.dao.*;
import lk.lokitha.alokagreen.dao.custom.*;
import lk.lokitha.alokagreen.dao.custom.impl.*;

import java.sql.SQLException;

public class NewId {
    private static final EmployeeDAO employeeDAO = (EmployeeDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.EMPLOYEE );
    private static final EmployeeAttendanceDAO employeeAttendanceDAO = (EmployeeAttendanceDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.ATTENDANCE );
    private static final EmployeeSalaryDAO employeeSalaryDAO = (EmployeeSalaryDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SALARY );
    private static final CustomerDAO customerDAO = (CustomerDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.CUSTOMER );
    private static final SupplierDAO supplierDAO = (SupplierDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SUPPLIER );
    private static final MaterialDAO materialDAO = (MaterialDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.MATERIAL );
    private static final MaterialStockDAO materialStockDAO = (MaterialStockDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.MATERIAL_STOCK );
    private static final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.PRODUCT );
    private static final ProductStockDAO productStockDAO = (ProductStockDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.PRODUCT_STOCK );
    private static final CustomerOrderDAO customerOrderDAO = (CustomerOrderDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.CUSTOMER_ORDER );
    private static final SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SUPPLIER_ORDER );
    private static final SpoiledReportDAO spoiledReportDAO = (SpoiledReportDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SPOILED );

    public static String newEmployeeId() throws SQLException {

        int lastId = employeeDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "E-0" + lastId;
        } else {
            return "E-01";
        }
    }

    public static String newCustomerId() throws SQLException {
        int lastId = customerDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "C-0" + lastId;
        } else {
            return "C-01";
        }
    }

    public static String newSupplierId() throws SQLException {
        int lastId = supplierDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "S-0" + lastId;
        } else {
            return "S-01";
        }
    }

    public static String newMaterialCode() throws SQLException {
        int lastId = materialDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "M-0" + lastId;
        } else {
            return "M-01";
        }
    }

    public static String newProductCode() throws SQLException {
        int lastId = productDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "P-0" + lastId;
        } else {
            return "P-01";
        }
    }

    public static String newProductStockCode() throws SQLException {
        int lastId = productStockDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "PS-0" + lastId;
        } else {
            return "PS-01";
        }
    }

    public static String newMaterialStockCode() throws SQLException {
        int lastId = materialStockDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "MS-0" + lastId;
        } else {
            return "MS-01";
        }
    }

    public static String newCustomerOrderId() throws SQLException {
        int lastId = customerOrderDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "CO-0" + lastId;
        } else {
            return "CO-01";
        }
    }

    public static String newSupplierOrderId() throws SQLException {
        int lastId = supplierOrderDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "SO-0" + lastId;
        } else {
            return "SO-01";
        }
    }

    public static String newAttendanceId() throws SQLException {
        int lastId = employeeAttendanceDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "EA-0" + lastId;
        } else {
            return "EA-01";
        }
    }

    public static String newSalaryId() throws SQLException {
        int lastId = employeeSalaryDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "ES-0" + lastId;
        } else {
            return "ES-01";
        }
    }

    public static String newSpoiledReportId() throws SQLException {
        int lastId = spoiledReportDAO.getLastId();

        if (lastId != -1) {
            lastId++;
            return "SR-0" + lastId;
        } else {
            return "SR-01";
        }
    }

}