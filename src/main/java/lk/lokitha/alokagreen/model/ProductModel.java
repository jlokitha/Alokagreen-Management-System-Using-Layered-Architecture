package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.ProductDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductModel {

    public static boolean saveProduct(final ProductDto productDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO product_List VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, productDto.getProduct_Code());
            ps.setString(2, productDto.getDescription());
            ps.setDouble(3, productDto.getUnit_Price());

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

            String query = "SELECT MAX(CAST(SUBSTRING(product_Code, 3) AS SIGNED)) AS max_value FROM product_List";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static ArrayList<String> getAllProductDesc() {

        ArrayList<String> product = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT description FROM product_List";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                product.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return product;
    }

    public static String getIdOfDesc(final String desc) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT product_Code FROM product_List WHERE description = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, desc);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static String getDescOfId(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT description FROM product_List WHERE product_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static ArrayList<String> getAllId() {
        try {
            DbConnection dbConnection = DbConnection.getInstance();
            Connection connection = dbConnection.getConnection();

            String query = "SELECT product_Code FROM product_List ORDER BY CAST(SUBSTRING(product_Code, 3) AS UNSIGNED)";

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

    public static ProductDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM product_List WHERE product_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            ProductDto productDto = new ProductDto();

            while (resultSet.next()) {
                productDto.setProduct_Code(resultSet.getString(1));
                productDto.setDescription(resultSet.getString(2));
                productDto.setUnit_Price(resultSet.getDouble(3));
            }

            return productDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String[] getDescUnitPriceOfId(final String id) {

        String[] data = new String[2];

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT description, unit_Price FROM product_List WHERE product_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                data[0] = resultSet.getString(1);
                data[1] = resultSet.getString(2);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return data;
    }

    public static boolean updateProduct(final ProductDto productDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE product_List SET description=?, unit_Price=? WHERE product_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(3, productDto.getProduct_Code());
            ps.setString(1, productDto.getDescription());
            ps.setDouble(2, productDto.getUnit_Price());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static boolean deleteProduct(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM product_List WHERE product_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

}
