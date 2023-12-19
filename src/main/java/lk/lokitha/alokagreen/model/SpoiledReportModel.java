package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.SpoiledReportDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SpoiledReportModel {

    public static boolean saveSpoiledReport(final SpoiledReportDto spoiledReportDto) throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO spoiled_Product_Report VALUES(?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, spoiledReportDto.getReport_Id());
            ps.setString(2, spoiledReportDto.getProduct_Code());
            ps.setDouble(3, spoiledReportDto.getSpoiled_Qty());
            ps.setString(4, spoiledReportDto.getDate());
            ps.setString(5, spoiledReportDto.getTime());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;


        return false;
    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT report_Id FROM spoiled_Product_Report ORDER BY date DESC, time DESC";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            ArrayList<String> ids = new ArrayList<>();

            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }

            return ids;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static SpoiledReportDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM spoiled_Product_Report WHERE report_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            SpoiledReportDto data = new SpoiledReportDto();

            while (resultSet.next()) {

                data.setReport_Id(resultSet.getString(1));
                data.setProduct_Code(resultSet.getString(2));
                data.setSpoiled_Qty(resultSet.getInt(3));
                data.setDate(resultSet.getString(4));
                data.setTime(resultSet.getString(5));
            }

            return data;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean UpdateSpoiledReport(final String... data) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE spoiled_Product_Report SET product_Code=?, spoiled_Qty=? WHERE report_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, data[1]);
            ps.setString(2, data[2]);
            ps.setString(3, data[0]);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static int getLastId() {

        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(report_Id, 4) AS SIGNED)) AS max_value FROM spoiled_Product_Report";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

}
