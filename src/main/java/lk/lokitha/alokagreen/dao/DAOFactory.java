package lk.lokitha.alokagreen.dao;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDaoFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOType {
        ATTENDANCE,CUSTOMER,CUSTOMER_ORDER,CUSTOMER_ORDER_DETAIL,EMPLOYEE,MATERIAL,MATERIAL_STOCK,PRODUCT,PRODUCT_STOCK,SALARY,SPOILED,SUPPLIER,SUPPLIER_ORDER,SUPPLIER_ORDER_DETAIL,USER
    }


}
