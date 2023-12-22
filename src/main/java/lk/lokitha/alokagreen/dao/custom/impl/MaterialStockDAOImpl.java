package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.MaterialStockDAO;
import lk.lokitha.alokagreen.dto.MaterialStockDto;
import lk.lokitha.alokagreen.dto.tm.MaterialStockTm;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialStockDAOImpl implements MaterialStockDAO {
    @Override
    public boolean updateMaterialStock(final String... data) throws SQLException {
        return SQLUtil.execute( "UPDATE material_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE stock_Id = ?;",
                data[1],
                data[0]
        );
    }

    @Override
    public boolean SaveMaterialStock(final String[] item, final String date) throws SQLException {
        return SQLUtil.execute( "INSERT INTO material_Stock VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                NewId.newMaterialStockCode(),
                item[1],
                item[2],
                item[2],
                item[3],
                date,
                item[4],
                item[5]
        );
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(stock_Id, 4) AS SIGNED)) AS max_value FROM material_Stock";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) {
            return rst.getInt(1);
        }

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT stock_Id FROM material_Stock ORDER BY CAST(SUBSTRING(stock_Id, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public MaterialStockTm getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM material_Stock WHERE stock_Id = ?", id );

        while (rst.next()) {
            return new MaterialStockTm(
                    rst.getString( 1 ),
                    null,
                    rst.getString( 3 ),
                    rst.getString( 7 ),
                    rst.getString( 8 )
            );
        }

        return null;
    }

    @Override
    public MaterialStockDto getDetail(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM material_Stock WHERE stock_Id = ?", id );

        while (rst.next()) {
            return new MaterialStockDto(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getInt( 3 ),
                    rst.getInt( 4 ),
                    rst.getDouble( 5 ),
                    rst.getString( 6 ),
                    rst.getString( 7 ),
                    rst.getString( 8 )
            );
        }

        return null;
    }

    @Override
    public int getProductQty(final String pId) throws SQLException {
        String sql = "SELECT qty_On_Hand FROM material_Stock " +
                "WHERE material_Code = ? AND status = 'Not Expired' AND exp_Date > CURDATE()";

        ResultSet resultSet = SQLUtil.execute( sql, pId );

        int qty = 0;

        while (resultSet.next()) {
            qty += resultSet.getInt(1);
        }

        return qty;
    }

    @Override
    public boolean UpdateMaterialStockExp(final String id) throws SQLException {
        return SQLUtil.execute( "UPDATE material_Stock SET status = 'Expired' WHERE stock_Id = ?", id );
    }

    @Override
    public boolean deleteMaterialStock(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM material_Stock WHERE stock_Id = ?", id );
    }
}
