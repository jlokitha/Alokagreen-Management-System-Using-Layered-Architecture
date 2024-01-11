package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.CustomerOrder;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface CustomerOrderDAO extends CrudUtil<CustomerOrder> {
    boolean updateCustomerOrder(final String[] data) throws SQLException;

    double getTotalIncome() throws SQLException;
}
