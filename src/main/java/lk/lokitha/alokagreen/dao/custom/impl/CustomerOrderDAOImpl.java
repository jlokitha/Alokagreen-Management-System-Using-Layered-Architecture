package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.CustomerOrderDAO;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderDAOImpl implements CustomerOrderDAO {
    @Override
    public String[] getStockDetails(final String sId) throws SQLException {
        String sql = "SELECT ps.exp_Date, ps.qty_On_Hand, pl.unit_Price " +
                "FROM product_Stock ps " +
                "JOIN product_List pl " +
                "ON ps.product_Code = pl.product_Code " +
                "WHERE ps.stock_ID = ?";

        ResultSet rst = SQLUtil.execute( sql, sId );

        String[] details = null;

        if (rst.next()) {
            details = new String[]{
                    rst.getString( 1 ),
                    rst.getString( 3 ),
                    rst.getString( 2 )
            };
        }

        return details;
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(customer_Order_Id, 4) AS SIGNED)) AS max_value FROM customer_Order";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) return rst.getInt(1);

        return -1;
    }

    @Override
    public boolean saveCustomerOrder(final CustomerOrderDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO customer_Order VALUES(?, ?, ?, ?, ?)",
                dto.getCustomer_Order_Id(),
                dto.getCustomer_Id(),
                dto.getTotal_Amount(),
                dto.getDate(),
                dto.getTime()
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
    public CustomerOrderDto getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM customer_Order WHERE customer_Order_Id = ?" );

        CustomerOrderDto cODto = null;

        while (rst.next()) {
            cODto = new CustomerOrderDto(
                   rst.getString( 1 ),
                   rst.getString( 2 ),
                   rst.getDouble( 3 ),
                   rst.getString( 4 ),
                   rst.getString( 5 )
            );
        }

        return cODto;
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
}
