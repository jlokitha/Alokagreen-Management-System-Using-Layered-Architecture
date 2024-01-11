package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.dao.SuperDAO;

import java.sql.SQLException;
import java.util.Map;

public interface CustomerOrderDetailDAO extends SuperDAO {
    boolean saveCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException;

    boolean deleteCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException;

    Map<String, String> getData(final String cOId) throws SQLException;
}
