package lk.lokitha.alokagreen.dao;

import lk.lokitha.alokagreen.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        ATTENDANCE,CUSTOMER,CUSTOMER_ORDER,CUSTOMER_ORDER_DETAIL,EMPLOYEE,MATERIAL,MATERIAL_STOCK,PRODUCT,PRODUCT_STOCK,SALARY,SPOILED,SUPPLIER,SUPPLIER_ORDER,SUPPLIER_ORDER_DETAIL,USER,QUERY
    }

    public SuperDAO getDAO(DAOType daoType) {
        switch (daoType) {
            case ATTENDANCE:
                return new EmployeeAttendanceDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case CUSTOMER_ORDER:
                return new CustomerOrderDAOImpl();
            case CUSTOMER_ORDER_DETAIL:
                return new CustomerOrderDetailDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case MATERIAL:
                return new MaterialDAOImpl();
            case MATERIAL_STOCK:
                return new MaterialStockDAOImpl();
            case PRODUCT:
                return new ProductDAOImpl();
            case PRODUCT_STOCK:
                return new ProductStockDAOImpl();
            case SALARY:
                return new EmployeeSalaryDAOImpl();
            case SPOILED:
                return new SpoiledReportDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderDAOImpl();
            case SUPPLIER_ORDER_DETAIL:
                return new SupplierOrderDetailDAOImpl();
            case USER:
                return new UserDAOImpl();
            case QUERY:
                return new QueryDAOImpl ();
            default:
                return null;
        }
    }
}
