package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.ProductDAO;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAOImpl implements ProductDAO {
    @Override
    public boolean saveProduct(final ProductDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO product_List VALUES(?, ?, ?)",
                dto.getProduct_Code(),
                dto.getDescription(),
                dto.getUnit_Price()
        );
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(product_Code, 3) AS SIGNED)) AS max_value FROM product_List";

        ResultSet resultSet = SQLUtil.execute( sql );

        if (resultSet.next()) return resultSet.getInt(1);

        return -1;
    }

    @Override
    public ArrayList<String> getAllProductDesc() throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT description FROM product_List" );

        ArrayList<String> product = new ArrayList<>();

        while (rst.next()) {
            product.add(rst.getString(1));
        }

        return product;
    }

    @Override
    public String getIdOfDesc(final String desc) throws SQLException {
        ResultSet rst =SQLUtil.execute( "SELECT product_Code FROM product_List WHERE description = ?", desc );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public String getDescOfId(final String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "SELECT description FROM product_List WHERE product_Code = ?",
                id
        );

        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT product_Code FROM product_List ORDER BY CAST(SUBSTRING(product_Code, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public ProductDto getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM product_List WHERE product_Code = ?", id);

        while (rst.next()) {
            return new ProductDto(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getDouble( 3 )
            );
        }

        return null;
    }

    @Override
    public String[] getDescUnitPriceOfId(final String id) throws SQLException {
        String sql = "SELECT description, unit_Price FROM product_List WHERE product_Code = ?";
        ResultSet rst = SQLUtil.execute( sql, id );

        String[] data = new String[2];

        if (rst.next()) {
            data[0] = rst.getString(1);
            data[1] = rst.getString(2);
        }

        return data;
    }

    @Override
    public boolean updateProduct(final ProductDto dto) throws SQLException {
        return SQLUtil.execute( "UPDATE product_List SET description=?, unit_Price=? WHERE product_Code = ?",
                dto.getDescription(),
                dto.getUnit_Price(),
                dto.getProduct_Code()
        );
    }

    @Override
    public boolean deleteProduct(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM product_List WHERE product_Code = ?", id );
    }
}
