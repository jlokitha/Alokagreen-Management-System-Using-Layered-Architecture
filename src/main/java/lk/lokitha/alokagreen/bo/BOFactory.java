package lk.lokitha.alokagreen.bo;

import lk.lokitha.alokagreen.bo.custom.impl.CustomerBOImpl;
import lk.lokitha.alokagreen.bo.custom.impl.EmployeeBOImpl;
import lk.lokitha.alokagreen.bo.custom.impl.SupplierBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER,EMPLOYEE,SUPPLIER
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            default:
                return null;
        }
    }
}
