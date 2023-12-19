package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.SpoiledReportDto;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.NewId;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class StockTransaction {

    public static boolean UpdateProductStockExp(final ArrayList<String> ids) throws SQLException {
        Connection connection = null;
        boolean result = false;

        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);

            boolean updated = ProductStockModel.UpdateProductStockExp(ids);

            if ( updated ) {
                SpoiledReportDto dto;
                boolean save = true;

                for (String id : ids) {
                    dto = new SpoiledReportDto();

                    dto.setReport_Id(NewId.newSpoiledReportId());
                    dto.setProduct_Code(ProductStockModel.getProductId(id));
                    dto.setSpoiled_Qty(ProductStockModel.getProductQtyOnHand(id));
                    dto.setDate(DateTime.dateNow());
                    dto.setTime(DateTime.timeNow());

                    if (!SpoiledReportModel.saveSpoiledReport(dto)) {
                        save = false;
                    }

                    if ( save ) {
                        connection.commit();
                        result = true;
                    }
                }
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return result;
    }
}
