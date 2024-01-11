package lk.ijse.alokagreen.dao.custom.impl;

import lk.ijse.alokagreen.dao.custom.ProductStockDAO;
import lk.ijse.alokagreen.entity.ProductStock;
import lk.ijse.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ProductStockDAOImpl implements ProductStockDAO {
    @Override
    public boolean save(ProductStock entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO product_Stock VALUES(?, ?, ?, ?, ?, ?, ?)",
                entity.getStockId(),
                entity.getProductCode(),
                entity.getQtyOnHand(),
                entity.getQty(),
                entity.getDate(),
                entity.getExpDate(),
                entity.getStatus()
        );
    }

    @Override
    public boolean update(ProductStock entity) throws SQLException {
        return SQLUtil.execute( "UPDATE product_Stock SET product_Code=?, qty_On_Hand=?, qty=?, exp_Date=?, status=? WHERE stock_Id = ?",
                entity.getProductCode(),
                entity.getQtyOnHand(),
                entity.getQty(),
                entity.getExpDate(),
                entity.getStatus(),
                entity.getStockId()
        );
    }

    @Override
    public ProductStock getData(String id) throws SQLException {
            ResultSet rst = SQLUtil.execute( "SELECT * FROM product_Stock WHERE stock_Id = ?", id);

            if (rst.next()) {
                return new ProductStock(
                        rst.getString( 1 ),
                        rst.getString( 2 ),
                        rst.getInt( 3 ),
                        rst.getInt( 4 ),
                        rst.getString( 5 ),
                        rst.getString( 6 ),
                        rst.getString( 7 )
                );
            }

            return null;
    }

    @Override
    public int getLastId() throws SQLException {
            String sql = "SELECT MAX(CAST(SUBSTRING(stock_Id, 4) AS SIGNED)) AS max_value FROM product_Stock";

            ResultSet rst = SQLUtil.execute( sql );

            if (rst.next()) {
                return rst.getInt(1);
            }

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT stock_Id FROM product_Stock ORDER BY CAST(SUBSTRING(stock_Id, 3) AS UNSIGNED)";

        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }
        return ids;
    }

    @Override
    public boolean updateProductStock(final Map<String, String> items) throws SQLException {
        String sql = "UPDATE product_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE stock_Id = ?";

        for (Map.Entry<String, String> entry : items.entrySet()) {
            boolean updated = SQLUtil.execute( sql, Integer.parseInt( entry.getValue() ), entry.getKey() );

            if (!updated) return false;
        }

        return true;
    }

    @Override
    public ArrayList<String> getAllIdOfPDesc(final String pDesc) throws SQLException {
        String query = "SELECT stock_Id, status FROM product_Stock WHERE product_Code = (SELECT product_Code FROM product_List WHERE description = ?) AND status = 'Not Expired'";
        ResultSet rst = SQLUtil.execute( query, pDesc );

        ArrayList<String> ids = new ArrayList<>( );

        while ( rst.next( ) ) {
            if ( rst.getString( 2 ) != "Expired" ) {
                ids.add( rst.getString( 1 ) );
            }
        }

        return ids;
    }

    @Override
    public String getProductId(final String stockId) throws SQLException {
            ResultSet rst = SQLUtil.execute( "SELECT product_Code FROM product_Stock WHERE stock_Id = ?",
                    stockId
            );

            if (rst.next()) {
                return rst.getString(1);
            }

        return null;
    }

    @Override
    public int getProductQty(final String pId) throws SQLException {
        String sql = "SELECT qty_On_Hand FROM product_Stock WHERE product_Code = ? AND status = 'Not Expired' AND exp_Date > CURDATE()";
        ResultSet resultSet = SQLUtil.execute( sql, pId );

        int qty = 0;

        while (resultSet.next()) {
            qty += resultSet.getInt(1);
        }

        return qty;
    }

    @Override
    public boolean UpdateProductStockExp(final ArrayList<String> ids) throws SQLException {
        for (int i = 0; i < ids.size(); i++) {
            boolean updated = SQLUtil.execute( "UPDATE product_Stock SET status = 'Expired' WHERE stock_Id = ?",
                    ids.get( i )
            );

            if (!updated) return false;
        }

        return true;
    }

    @Override
    public int getProductQtyOnHand(final String pId) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT qty_On_Hand FROM product_Stock WHERE stock_Id = ?",
                pId
        );

        int qty = 0;

        if (rst.next()) {
            qty += rst.getInt(1);
        }

        return qty;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }
}
