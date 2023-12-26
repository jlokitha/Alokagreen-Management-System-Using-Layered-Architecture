package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.SupplierOrderDetailDAO;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailDAOImpl implements SupplierOrderDetailDAO {

    @Override
    public boolean saveSupplierOrderDetail(final String sOI, final ArrayList<String[]> items) throws SQLException {
        for (int i = 0; i < items.size(); i++) {
            boolean saved = SQLUtil.execute( "INSERT INTO supplier_Order_Detail VALUES(?, ?)",
                    sOI,
                    items.get( i )[0]
            );

            if (!saved) return false;
        }

        return true;
    }

    @Override
    public String getOrderId(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT supplier_Order_Id FROM supplier_Order_Detail WHERE stock_Id = ?",
                id
        );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public ArrayList<String> getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT stock_Id FROM supplier_Order_Detail WHERE supplier_Order_Id = ?",
                id
        );

        ArrayList<String> list = new ArrayList<>();

        while (rst.next()) {

            list.add(rst.getString(1));
        }

        return list;
    }
}
