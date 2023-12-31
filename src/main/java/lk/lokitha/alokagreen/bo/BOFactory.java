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
        CUSTOMER,EMPLOYEE,SUPPLIER,MATERIAL,PRODUCT,ATTENDANCE,SALARY
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
            default:
                return null;
        }
    }
}
