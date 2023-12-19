package lk.lokitha.alokagreen.dao.custom;

import java.sql.SQLException;
import java.util.Map;

public interface CustomerOrderDetailDAO {
    boolean saveCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException;

    boolean deleteCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException;

    Map<String, String> getData(final String cOId) throws SQLException;
}
