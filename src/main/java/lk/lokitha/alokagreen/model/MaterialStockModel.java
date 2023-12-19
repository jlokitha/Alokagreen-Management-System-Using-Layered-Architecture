package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.MaterialStockDto;
import lk.lokitha.alokagreen.dto.tm.MaterialStockTm;
import lk.lokitha.alokagreen.util.NewId;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialStockModel {

    public static boolean updateMaterialStock(final String... data) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE material_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE stock_Id = ?;";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, data[1]);
            ps.setString(2, data[0]);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static boolean SaveMaterialStock(final ArrayList<String[]> items, final String date) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO material_Stock VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        for (int i = 0; i < items.size(); i++) {

            items.get(i)[0] = NewId.newMaterialStockCode();
            ps.setString(1, items.get(i)[0]);
            ps.setString(2, items.get(i)[1]);
            ps.setString(3, items.get(i)[2]);
            ps.setString(4, items.get(i)[2]);
            ps.setString(5, items.get(i)[3]);
            ps.setString(6, date);
            ps.setString(7, items.get(i)[4]);
            ps.setString(8, items.get( i )[5]);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) return false;
        }

        return true;
    }

    public static int getLastId() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(stock_Id, 4) AS SIGNED)) AS max_value FROM material_Stock";

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

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT stock_Id FROM material_Stock ORDER BY CAST(SUBSTRING(stock_Id, 3) AS UNSIGNED)";

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

    public static MaterialStockTm getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM material_Stock WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            MaterialStockTm mST = null;

            while (resultSet.next()) {
                mST = new MaterialStockTm();

                mST.setStock_Id(resultSet.getString(1));
                mST.setDesc(MaterialModel.getDescOfId(resultSet.getString(2)));
                mST.setQty(resultSet.getString(3));
                mST.setExp_Date(resultSet.getString(7));
                mST.setStatus(resultSet.getString(8));
            }

            return mST;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static MaterialStockDto getDetail(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM material_Stock WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            MaterialStockDto mSDto = new MaterialStockDto();

            while (resultSet.next()) {
                mSDto.setStock_Id(resultSet.getString(1));
                mSDto.setMaterial_Code(resultSet.getString(2));
                mSDto.setQty_On_Hand(resultSet.getInt(3));
                mSDto.setQty(resultSet.getInt(4));
                mSDto.setUnit_Price(resultSet.getDouble(5));
                mSDto.setExp_Date(resultSet.getString(7));
                mSDto.setStatus(resultSet.getString(8));
            }

            return mSDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getProductQty(final String pId) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT qty_On_Hand FROM material_Stock WHERE material_Code = ? AND status = 'Not Expired' AND exp_Date > CURDATE()";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, pId);

            ResultSet resultSet = ps.executeQuery();

            int qty = 0;

            while (resultSet.next()) {

                qty += resultSet.getInt(1);
            }

            return qty;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean UpdateMaterialStockExp(final ArrayList<String> ids) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "UPDATE material_Stock SET status = 'Expired' WHERE stock_Id = ?";

        PreparedStatement ps = connection.prepareStatement(query);

        for (int i = 0; i < ids.size(); i++) {

            ps.setString(1, ids.get(i));

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) return false;
        }

        return true;
    }
    
    public static boolean deleteMaterialStock(final String id) throws SQLException {
        
        Connection connection = DbConnection.getInstance().getConnection();
        
        String query = "DELETE FROM material_Stock WHERE stock_Id = ?";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, id);

        return ps.executeUpdate() > 0;
    }


}
