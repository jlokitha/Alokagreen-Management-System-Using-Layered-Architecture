package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.CustomerOrderDetailDAO;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrderDetailDAOImpl implements CustomerOrderDetailDAO {
    @Override
    public boolean saveCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException {
        for (Map.Entry<String, String> entry : items.entrySet()) {
            boolean isSaved = SQLUtil.execute( "INSERT INTO customer_Order_Detail VALUES(?, ?, ?)",
                    cOI,
                    entry.getKey(),
                    Integer.parseInt( entry.getValue() )
            );

            if (!isSaved) return false;
        }

        return true;
    }

    @Override
    public boolean deleteCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException {
        String sql = "DELETE FROM customer_Order_Detail WHERE Customer_Order_Id=? AND stock_Id=? AND qty=?";

        for (Map.Entry<String, String> entry : items.entrySet()) {
            boolean isDeleted = SQLUtil.execute( sql, cOI, entry.getKey(), entry.getValue());

            if (!isDeleted) return false;
        }

        return true;
    }

    @Override
    public Map<String, String> getData(final String cOId) throws SQLException {
        String sql = "SELECT stock_Id, qty FROM customer_Order_Detail WHERE customer_Order_Id = ?";
        ResultSet rst = SQLUtil.execute( sql, cOId );

        Map<String, String> list = new HashMap<>();

        while (rst.next()) {
            list.put(
                    rst.getString(1),
                    rst.getString(2)
            );
        }

        return list;
    }
}
