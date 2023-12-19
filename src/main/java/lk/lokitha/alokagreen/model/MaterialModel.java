package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.MaterialDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialModel {

    public static boolean saveMaterial(final MaterialDto materialDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO material_List VALUES(?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, materialDto.getMaterial_Code());
            ps.setString(2, materialDto.getDescription());

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

            String query = "SELECT MAX(CAST(SUBSTRING(material_Code, 3) AS SIGNED)) AS max_value FROM material_List";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getInt(1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static ArrayList<String> getAllMaterialId() {

        ArrayList<String> product = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT material_Code FROM material_List ORDER BY CAST(SUBSTRING(material_Code, 3) AS UNSIGNED)";

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

    public static ArrayList<String> getAllMaterialDesc() {

        ArrayList<String> product = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT description FROM material_List";

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

    public static String getDescOfId(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT description FROM material_List WHERE material_Code = ?";

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

    public static String getIdOfDesc(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT material_Code FROM material_List WHERE description = ?";

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

    public static MaterialDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM material_List WHERE material_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            MaterialDto mDto = new MaterialDto();

            while (resultSet.next()) {
                mDto.setMaterial_Code(resultSet.getString(1));
                mDto.setDescription(resultSet.getString(2));
            }

            return mDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateMaterial(final MaterialDto materialDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE material_List SET description=? WHERE material_Code = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(2, materialDto.getMaterial_Code());
            ps.setString(1, materialDto.getDescription());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static boolean deleteMaterial(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM material_List WHERE material_Code = ?";

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
