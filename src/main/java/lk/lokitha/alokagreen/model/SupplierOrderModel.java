package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.SupplierOrderDto;
import lk.lokitha.alokagreen.dto.tm.SupplierOrderTm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderModel {

    public static int getLastId() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(supplier_Order_Id, 4) AS SIGNED)) AS max_value FROM supplier_Order";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static boolean saveSupplierOrder(final SupplierOrderDto supplierOrderDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO supplier_Order VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, supplierOrderDto.getSupplier_Order_Id());
        ps.setString(2, supplierOrderDto.getSupplier_Id());
        ps.setDouble(3, supplierOrderDto.getTotal_Amount());
        ps.setString(4, supplierOrderDto.getDate());
        ps.setString(5, supplierOrderDto.getTime());

        return ps.executeUpdate() > 0;

    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT supplier_Order_Id FROM supplier_Order ORDER BY CAST(SUBSTRING(supplier_Order_Id, 3) AS UNSIGNED)";

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

    public static SupplierOrderTm getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM supplier_Order WHERE supplier_Order_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            SupplierOrderTm sTm = new SupplierOrderTm();

            while (resultSet.next()) {
                sTm.setSupplier_Order_Id(resultSet.getString(1));
                sTm.setSupplier_Id(resultSet.getString(2));
                sTm.setTotal_Amount(resultSet.getDouble(3));
                sTm.setDate(resultSet.getString(4));
                sTm.setTime(resultSet.getString(5));
            }

            return sTm;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getSupplierId(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT supplier_Id FROM supplier_Order WHERE supplier_Order_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps .setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                return resultSet.getString(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static double getTotalExpense() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT SUM(total_Amount) AS total_order_sum FROM supplier_Order";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getDouble(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0.00;
    }

}
