package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CustomerOrderDetailModel {

    public static boolean saveCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException {

            Connection connection = DbConnection.getInstance().getConnection();

            String query = "INSERT INTO customer_Order_Detail VALUES(?, ?, ?)";

            PreparedStatement ps = connection.prepareStatement(query);

            for (Map.Entry<String, String> entry : items.entrySet()) {

                ps.setString(1, cOI);
                ps.setString(2, entry.getKey());
                ps.setInt(3, Integer.parseInt(entry.getValue()));

                int affectedRows = ps.executeUpdate();

                if (affectedRows == 0) return false;
            }

        return true;
    }

    public static boolean deleteCustomerOrderDetail(final String cOI, final Map<String, String> items) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "DELETE FROM customer_Order_Detail WHERE Customer_Order_Id=? AND stock_Id=? AND qty=?";

        PreparedStatement ps = connection.prepareStatement(query);

        for (Map.Entry<String, String> entry : items.entrySet()) {

            ps.setString(1, cOI);
            ps.setString(2, entry.getKey());
            ps.setString(3, entry.getValue());

            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) return false;
        }

        return true;
    }

    public static Map<String, String> getData(final String cOId){

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT stock_Id, qty FROM customer_Order_Detail WHERE customer_Order_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, cOId);

            ResultSet resultSet = ps.executeQuery();

            Map<String, String> list = new HashMap<>();

            while (resultSet.next()) {

                String stockId = resultSet.getString(1);
                String qty = resultSet.getString(2);

                list.put(stockId, qty);
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
