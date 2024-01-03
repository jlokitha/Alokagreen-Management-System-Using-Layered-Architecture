package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.QueryDAO;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class QueryDAOImpl implements QueryDAO {
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
}
