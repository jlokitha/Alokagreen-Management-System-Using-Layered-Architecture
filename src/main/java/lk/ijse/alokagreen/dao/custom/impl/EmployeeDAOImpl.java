package lk.ijse.alokagreen.dao.custom.impl;

import lk.ijse.alokagreen.dao.custom.EmployeeDAO;
import lk.ijse.alokagreen.entity.Employee;
import lk.ijse.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean save(final Employee entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO employee VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                entity.getEmployeeId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getHouseNo(),
                entity.getStreet(),
                entity.getCity(),
                entity.getMobile(),
                entity.getEmail(),
                entity.getRole(),
                entity.getDate()
        );
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(employee_Id, 3) AS SIGNED)) AS max_value FROM employee";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) {
            return rst.getInt(1);
        }

        return -1;
    }

    @Override
    public String getNameOfId(final String id) throws SQLException {
        String sql = "SELECT first_Name, last_Name FROM employee WHERE employee_Id = ?";
        ResultSet rst = SQLUtil.execute( sql, id );

        if (rst.next()) {
            return (rst.getString(1) + " " + rst.getString(2));
        }

        return null;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT employee_Id FROM employee ORDER BY CAST(SUBSTRING(employee_Id, 3) AS UNSIGNED)";
        ResultSet resultSet = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }

        return ids;
    }

    @Override
    public boolean update(final Employee entity) throws SQLException {
        String sql = "UPDATE employee " +
                "SET first_Name=?, last_Name=?, nic=?, house_No=?, street=?, city=?, mobile=?, email=?, role=? " +
                "WHERE employee_Id = ?";

        return SQLUtil.execute( sql,
                entity.getFirstName(),
                entity.getLastName(),
                entity.getNic(),
                entity.getHouseNo(),
                entity.getStreet(),
                entity.getCity(),
                entity.getMobile(),
                entity.getEmail(),
                entity.getRole(),
                entity.getEmployeeId()
        );
    }

    @Override
    public Employee getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM employee WHERE employee_Id = ?", id );

        while (rst.next()) {
            return new Employee(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 ),
                    rst.getString( 5 ),
                    rst.getString( 6 ),
                    rst.getString( 7 ),
                    rst.getString( 8 ),
                    rst.getString( 9 ),
                    rst.getString( 10 ),
                    rst.getString( 11 )
            );
        }

        return null;
    }

    @Override
    public boolean delete(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM employee WHERE employee_Id = ?", id );
    }

    @Override
    public String getEmailOfId(final String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute( "SELECT email FROM employee WHERE employee_Id = ?", id );

        if (resultSet.next()) {
            return resultSet.getString(1);
        }

        return null;
    }
}
