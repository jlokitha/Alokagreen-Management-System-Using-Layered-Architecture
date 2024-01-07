package lk.lokitha.alokagreen.bo;

import lk.lokitha.alokagreen.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER,EMPLOYEE,SUPPLIER,MATERIAL,PRODUCT,ATTENDANCE,SALARY,DASHBOARD,MATERIAL_STOCK,PRODUCT_STOCK,PROFILE,CUSTOMER_ORDER,SUPPLIER_ORDER,SIGN_IN,SIGN_UP
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case MATERIAL:
                return new MaterialBOImpl();
            case PRODUCT:
                return new ProductBOImpl();
            case ATTENDANCE:
                return new AttendanceBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case MATERIAL_STOCK:
                return new MaterialStockBOImpl();
            case PRODUCT_STOCK:
                return new ProductStockBOImpl();
            case PROFILE:
                return new ProfileBOImpl();
            case CUSTOMER_ORDER:
                return new CustomerOrderBOImpl();
            case SUPPLIER_ORDER:
                return new SupplierOrderBOImpl();
            case SIGN_IN:
                return new SignInBOImpl();
            case SIGN_UP:
                return new SignUpBOImpl();
            default:
                return null;
        }
    }
}
