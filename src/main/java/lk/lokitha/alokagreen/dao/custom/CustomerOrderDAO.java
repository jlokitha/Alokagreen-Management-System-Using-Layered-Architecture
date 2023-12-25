package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.entity.CustomerOrder;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDAO extends CrudUtil<CustomerOrder> {
    String[] getStockDetails(final String sId) throws SQLException;

    boolean updateCustomerOrder(final String[] data) throws SQLException;

    double getTotalIncome() throws SQLException;
}
