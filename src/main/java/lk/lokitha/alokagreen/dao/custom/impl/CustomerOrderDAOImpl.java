package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.CustomerOrderDAO;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.entity.CustomerOrder;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDAOImpl implements CustomerOrderDAO {

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(customer_Order_Id, 4) AS SIGNED)) AS max_value FROM customer_Order";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) return rst.getInt(1);

        return -1;
    }

    @Override
    public boolean save(final CustomerOrder entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO customer_Order VALUES(?, ?, ?, ?, ?)",
                entity.getCustomerOrderId(),
                entity.getCustomerId(),
                entity.getTotalAmount(),
                entity.getDate(),
                entity.getTime()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT customer_Order_Id FROM customer_Order ORDER BY CAST(SUBSTRING(customer_Order_Id, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public CustomerOrder getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM customer_Order WHERE customer_Order_Id = ?" );

        CustomerOrder entity = null;

        while (rst.next()) {
            entity = new CustomerOrder(
                   rst.getString( 1 ),
                   rst.getString( 2 ),
                   rst.getDouble( 3 ),
                   rst.getString ( 4 ),
                   rst.getString ( 5 )
            );
        }

        return entity;
    }

    @Override
    public boolean updateCustomerOrder(final String[] data) throws SQLException {
        return SQLUtil.execute( "UPDATE customer_Order SET customer_Id=?, total_Amount=? WHERE customer_Order_Id = ?",
                data[1],
                Double.parseDouble(data[2]),
                data[0]
        );
    }

    @Override
    public double getTotalIncome() throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT SUM(total_Amount) AS total_order_sum FROM customer_Order" );

        if (rst.next()) return rst.getDouble(1);

        return 0.00;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(CustomerOrder entity) throws SQLException {
        return false;
    }
}
