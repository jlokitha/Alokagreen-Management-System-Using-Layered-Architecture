package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.SpoiledReportDAO;
import lk.lokitha.alokagreen.entity.SpoiledProductReport;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpoiledReportDAOImpl implements SpoiledReportDAO {
    @Override
    public boolean save(SpoiledProductReport entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO spoiled_Product_Report VALUES(?, ?, ?, ?, ?)",
                entity.getReportId(),
                entity.getProductCode(),
                entity.getSpoiledQty(),
                entity.getDate(),
                entity.getTime()
        );
    }

    @Override
    public SpoiledProductReport getData(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM spoiled_Product_Report WHERE report_Id = ?",
                id
        );

        while (rst.next()) {
            return new SpoiledProductReport(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getInt( 3 ),
                    rst.getDate( 4 ),
                    rst.getTime( 5 )
            );
        }

        return null;
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(report_Id, 4) AS SIGNED)) AS max_value FROM spoiled_Product_Report";
        ResultSet resultSet = SQLUtil.execute( sql );

        if (resultSet.next()) {
            return resultSet.getInt(1);
        }

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet rst = SQLUtil.execute(
                "SELECT report_Id FROM spoiled_Product_Report ORDER BY date DESC, time DESC"
        );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public boolean UpdateSpoiledReport(final String... data) throws SQLException {
        return SQLUtil.execute( "UPDATE spoiled_Product_Report SET product_Code=?, spoiled_Qty=? WHERE report_Id = ?",
                data[1],
                data[2],
                data[0]
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update(SpoiledProductReport entity) throws SQLException {
        return false;
    }
}
