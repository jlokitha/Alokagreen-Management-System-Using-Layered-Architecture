package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierOrderDetailDAO extends SuperDAO {
    boolean saveSupplierOrderDetail(final String sOI, final ArrayList<String[]> items) throws SQLException;

    String getOrderId(final String id) throws SQLException;

    ArrayList<String> getData(final String id) throws SQLException;
}
