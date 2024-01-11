package lk.ijse.alokagreen.dao.custom.impl;

import lk.ijse.alokagreen.dao.custom.SupplierOrderDAO;
import lk.ijse.alokagreen.entity.SupplierOrder;
import lk.ijse.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDAOImpl implements SupplierOrderDAO {
    @Override
    public boolean save(SupplierOrder entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO supplier_Order VALUES(?, ?, ?, ?, ?)",
                entity.getSupplierOrderId(),
                entity.getSupplierId(),
                entity.getTotalAmount(),
                entity.getDate(),
                entity.getTime()
        );
    }

    @Override
    public SupplierOrder getData(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM supplier_Order WHERE supplier_Order_Id = ?",
                id
        );

        while (rst.next()) {
            return new SupplierOrder(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getDouble( 3 ),
                    rst.getString ( 4 ),
                    rst.getString ( 5 )
            );
        }

        return null;
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(supplier_Order_Id, 4) AS SIGNED)) AS max_value FROM supplier_Order";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) return rst.getInt(1);

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT supplier_Order_Id FROM supplier_Order ORDER BY CAST(SUBSTRING(supplier_Order_Id, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public String getSupplierId(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT supplier_Id FROM supplier_Order WHERE supplier_Order_Id = ?",
                id
        );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public double getTotalExpense() throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "SELECT SUM(total_Amount) AS total_order_sum FROM supplier_Order" );

        if (resultSet.next()) return resultSet.getDouble(1);

        return 0.00;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SupplierOrder entity) throws SQLException {
        return false;
    }
}
