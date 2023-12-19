package lk.lokitha.alokagreen.model;

import javafx.scene.control.Alert;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierModel {

    public static boolean saveSupplier(final SupplierDto supplierDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, supplierDto.getSupplier_Id());
            ps.setString(2, supplierDto.getCompany_Name());
            ps.setString(3, supplierDto.getCompany_Email());
            ps.setString(4, supplierDto.getCompany_Mobile());
            ps.setString(5, supplierDto.getCompany_Location());
            ps.setString(6, supplierDto.getTime());
            ps.setString(7, supplierDto.getDate());

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

            String query = "SELECT MAX(CAST(SUBSTRING(supplier_Id, 3) AS SIGNED)) AS max_value FROM supplier";

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

        ArrayList<String> ids = new ArrayList<>();

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT supplier_Id FROM supplier ORDER BY CAST(SUBSTRING(supplier_Id, 3) AS UNSIGNED)";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ids.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return ids;
    }

    public static String getNameOfId(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT company_Name FROM supplier WHERE supplier_Id = ?";

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

    public static SupplierDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM supplier WHERE supplier_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            SupplierDto supDto = new SupplierDto();

            while (resultSet.next()) {
                supDto.setSupplier_Id(resultSet.getString(1));
                supDto.setCompany_Name(resultSet.getString(2));
                supDto.setCompany_Email(resultSet.getString(3));
                supDto.setCompany_Mobile(resultSet.getString(4));
                supDto.setCompany_Location(resultSet.getString(5));
                supDto.setDate(resultSet.getString(6));
                supDto.setTime(resultSet.getString(7));
            }

            return supDto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateSupplier(final SupplierDto supplierDto) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "UPDATE supplier SET company_Name=?, company_Email=?, company_Mobile=?, company_Location=? WHERE supplier_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, supplierDto.getCompany_Name());
            ps.setString(2, supplierDto.getCompany_Email());
            ps.setString(3, supplierDto.getCompany_Mobile());
            ps.setString(4, supplierDto.getCompany_Location());
            ps.setString(5, supplierDto.getSupplier_Id());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) return true;

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        return false;
    }

    public static boolean deleteSupplier(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "DELETE FROM supplier WHERE supplier_Id = ?";

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

    public static String getIdOfName(final String id) {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT supplier_Id FROM supplier WHERE company_Name = ?";

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

}
