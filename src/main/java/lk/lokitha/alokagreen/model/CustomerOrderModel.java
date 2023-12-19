package lk.lokitha.alokagreen.model;

import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerOrderModel {

    public static String[] getStockDetails(final String sId) {

        String[] details = null;

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT ps.exp_Date, ps.qty_On_Hand, pl.unit_Price FROM product_Stock ps JOIN product_List pl ON ps.product_Code = pl.product_Code WHERE ps.stock_ID = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, sId);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {

                details = new String[3];

                details[0] = resultSet.getString(1);
                details[1] = resultSet.getString(3);
                details[2] = resultSet.getString(2);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return details;
    }

    public static int getLastId() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT MAX(CAST(SUBSTRING(customer_Order_Id, 4) AS SIGNED)) AS max_value FROM customer_Order";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getInt(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return -1;
    }

    public static boolean saveCustomerOrder(final CustomerOrderDto customerOrderDto) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "INSERT INTO customer_Order VALUES(?, ?, ?, ?, ?)";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, customerOrderDto.getCustomer_Order_Id());
        ps.setString(2, customerOrderDto.getCustomer_Id());
        ps.setDouble(3, customerOrderDto.getTotal_Amount());
        ps.setString(4, customerOrderDto.getDate());
        ps.setString(5, customerOrderDto.getTime());

        return ps.executeUpdate() > 0;

    }

    public static ArrayList<String> getAllId() {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT customer_Order_Id FROM customer_Order ORDER BY CAST(SUBSTRING(customer_Order_Id, 3) AS UNSIGNED)";

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

    public static CustomerOrderDto getData(final String id) {
        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT * FROM customer_Order WHERE customer_Order_Id = ?";

            PreparedStatement ps = connection.prepareStatement(query);

            ps.setString(1, id);

            ResultSet resultSet = ps.executeQuery();

            CustomerOrderDto cODto = null;

            while (resultSet.next()) {
                cODto = new CustomerOrderDto();

                cODto.setCustomer_Order_Id(resultSet.getString(1));
                cODto.setCustomer_Id(resultSet.getString(2));
                cODto.setTotal_Amount(resultSet.getDouble(3));
                cODto.setDate(resultSet.getString(4));
                cODto.setTime(resultSet.getString(5));
            }

            return cODto;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean updateCustomerOrder(final String[] data) throws SQLException {

        Connection connection = DbConnection.getInstance().getConnection();

        String query = "UPDATE customer_Order SET customer_Id=?, total_Amount=? WHERE customer_Order_Id = ?";

        PreparedStatement ps = connection.prepareStatement(query);

        ps.setString(1, data[1]);
        ps.setDouble(2, Double.parseDouble(data[2]));
        ps.setString(3, data[0]);

        return ps.executeUpdate() > 0;

    }

    public static double getTotalIncome() {

        try {
            Connection connection = DbConnection.getInstance().getConnection();

            String query = "SELECT SUM(total_Amount) AS total_order_sum FROM customer_Order";

            PreparedStatement ps = connection.prepareStatement(query);

            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) return resultSet.getDouble(1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return 0.00;
    }

}
