package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.ProductStockDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class ProductStockModel {

    public static boolean saveProductStock(final ProductStockDto productStockDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO product_Stock VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, productStockDto.getStock_Id());
            ps.setString(2, productStockDto.getProduct_Code());
            ps.setDouble(3, productStockDto.getQty_On_Hand());
            ps.setDouble(4, productStockDto.getQty());
            ps.setString(5, productStockDto.getDate());
            ps.setString(6, productStockDto.getExp_Date());
            ps.setString(7, productStockDto.getStatus());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static int getLastId() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(stock_Id, 4) AS SIGNED)) AS max_value FROM product_Stock";

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

    public static ArrayList<String> getAllIdOfPDesc(final String pDesc) {

        ArrayList<String> ids = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT stock_Id, status FROM product_Stock WHERE product_Code = (SELECT product_Code FROM product_List WHERE description = ?) AND status = 'Not Expired'";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, pDesc);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString(2) != "Expired") {
                    ids.add(resultSet.getString(1));
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ids;
    }

    public static boolean updateProductStock(final Map<String, String> items) throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE product_Stock SET qty_On_Hand = qty_On_Hand - ? WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            for (Map.Entry<String, String> entry : items.entrySet()) {

                ps.setInt(1, Integer.parseInt(entry.getValue()));
                ps.setString(2, entry.getKey());

                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) return false;
            }

        return true;
    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT stock_Id FROM product_Stock ORDER BY CAST(SUBSTRING(stock_Id, 3) AS UNSIGNED)";

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

    public static ProductStockDto getData(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM product_Stock WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            ProductStockDto pSDto = new ProductStockDto();

            while (resultSet.next()) {
                String[] data = ProductModel.getDescUnitPriceOfId(resultSet.getString(2));

                pSDto.setStock_Id(resultSet.getString(1));
                pSDto.setProduct_Code(resultSet.getString(2));
                pSDto.setQty_On_Hand(resultSet.getInt(3));
                pSDto.setQty(resultSet.getInt(4));
                pSDto.setDate(resultSet.getString(5));
                pSDto.setExp_Date(resultSet.getString(6));
                pSDto.setStatus(resultSet.getString(7));

            }

            return pSDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getProductId(final String stockId) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT product_Code FROM product_Stock WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, stockId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static boolean updateProductStock(final ProductStockDto productStockDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE product_Stock SET product_Code=?, qty_On_Hand=?, qty=?, exp_Date=?, status=? WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(6, productStockDto.getStock_Id());
            ps.setString(1, productStockDto.getProduct_Code());
            ps.setInt(2, productStockDto.getQty_On_Hand());
            ps.setInt(3, productStockDto.getQty());
            ps.setString(4, productStockDto.getExp_Date());
            ps.setString(5, productStockDto.getStatus());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static int getProductQty(final String pId) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT qty_On_Hand FROM product_Stock WHERE product_Code = ? AND status = 'Not Expired' AND exp_Date > CURDATE()";

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

    public static boolean UpdateProductStockExp(final ArrayList<String> ids) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "UPDATE product_Stock SET status = 'Expired' WHERE stock_Id = ?";

        PreparedStatement ps = connection.prepareStatement(query);

        for (int i = 0; i < ids.size(); i++) {

            ps.setString(1, ids.get(i));

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) return false;
        }

        return true;
    }

    public static int getProductQtyOnHand(final String pId) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT qty_On_Hand FROM product_Stock WHERE stock_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, pId);

            ResultSet resultSet = ps.executeQuery();

            int qty = 0;

            if (resultSet.next()) {

                qty += resultSet.getInt(1);
            }

            return qty;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
