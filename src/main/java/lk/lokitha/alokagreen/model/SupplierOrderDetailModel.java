package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierOrderDetailModel {

    public static boolean saveSupplierOrderDetail(final String sOI, final ArrayList<String[]> items) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO supplier_Order_Detail VALUES(?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        for (int i = 0; i < items.size(); i++) {

            ps.setString(1, sOI);
            ps.setString(2, items.get(i)[0]);

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) return false;
        }

        return true;
    }

    public static String getOrderId(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT supplier_Order_Id FROM supplier_Order_Detail WHERE stock_Id = ?";

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

    public static ArrayList<String> getData(final String cOId){

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT stock_Id FROM supplier_Order_Detail WHERE supplier_Order_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, cOId);

            ResultSet resultSet = ps.executeQuery();

            ArrayList<String> list = new ArrayList<>();

            while (resultSet.next()) {

                list.add(resultSet.getString(1));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
