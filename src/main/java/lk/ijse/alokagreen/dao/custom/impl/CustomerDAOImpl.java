package lk.ijse.alokagreen.dao.custom.impl;

import lk.ijse.alokagreen.dao.custom.CustomerDAO;
import lk.ijse.alokagreen.entity.Customer;
import lk.ijse.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public boolean save(final Customer dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO customer VALUES(?, ?, ?, ?, ?, ?, ?)",
                dto.getCustomerId(),
                dto.getName(),
                dto.getMobile(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getDate(),
                dto.getTime()
        );
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(customer_Id, 3) AS SIGNED)) AS max_value FROM customer";

        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) {
            return rst.getInt( 1 );
        }

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT customer_Id FROM customer ORDER BY CAST(SUBSTRING(customer_Id, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while ( rst.next() ) {
            ids.add( rst.getString( 1 ) );
        }

        return ids;
    }

    @Override
    public ArrayList<String> getAllMobile() throws SQLException {
        String sql = "SELECT mobile FROM customer ORDER BY CAST(SUBSTRING(customer_Id, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
                ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public String getNameOfId(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT name FROM customer WHERE customer_Id = ?", id );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public String getNameOfMobile(final String mobile) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT name FROM customer WHERE mobile = ?", mobile );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public String getIdOfMobile(final String mobile) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT customer_Id FROM customer WHERE mobile = ?", mobile );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public Customer getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM customer WHERE customer_Id = ?" ,id);

        while (rst.next()) {
            return new Customer(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getString(6),
                    rst.getString(7)
            );
        }

        return null;
    }

    @Override
    public boolean update(final Customer dto) throws SQLException {
            String sql = "UPDATE customer SET name=?, mobile=?, email=?, address=? WHERE customer_Id = ?";

            return SQLUtil.execute( sql,
                    dto.getName(),
                    dto.getMobile(),
                    dto.getEmail(),
                    dto.getAddress(),
                    dto.getCustomerId()
            );
    }

    @Override
    public boolean delete(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM customer WHERE customer_Id = ?", id );
    }
}
