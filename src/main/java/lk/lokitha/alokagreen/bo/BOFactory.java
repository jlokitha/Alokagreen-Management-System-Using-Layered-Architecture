package lk.lokitha.alokagreen.bo;

import lk.lokitha.alokagreen.bo.custom.impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType {
        CUSTOMER
    }

    public SuperBO getBO(BOType boType) {
        switch (boType) {
            case CUSTOMER:
                return new CustomerBOImpl();
            default:
                return null;
        }
    }
}
