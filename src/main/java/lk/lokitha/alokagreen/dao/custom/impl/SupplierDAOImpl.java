package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.SupplierDAO;
import lk.lokitha.alokagreen.entity.Supplier;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean save(Supplier entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO supplier VALUES(?, ?, ?, ?, ?, ?, ?)",
                entity.getSupplierId(),
                entity.getCompanyName(),
                entity.getCompanyEmail(),
                entity.getCompanyMobile(),
                entity.getCompanyLocation(),
                entity.getTime(),
                entity.getDate()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM supplier WHERE supplier_Id = ?", id );
    }

    @Override
    public boolean update(Supplier entity) throws SQLException {
        String sql = "UPDATE supplier SET company_Name=?, company_Email=?, company_Mobile=?, company_Location=? WHERE supplier_Id = ?";

        return SQLUtil.execute(
                sql,
                entity.getCompanyName(),
                entity.getCompanyEmail(),
                entity.getCompanyMobile(),
                entity.getCompanyLocation(),
                entity.getSupplierId()
        );
    }

    @Override
    public Supplier getData(String id) throws SQLException {
            ResultSet rst = SQLUtil.execute( "SELECT * FROM supplier WHERE supplier_Id = ?", id );

            while (rst.next()) {
                return new Supplier(
                        rst.getString( 1 ),
                        rst.getString( 2 ),
                        rst.getString( 3 ),
                        rst.getString( 4 ),
                        rst.getString( 5 ),
                        rst.getTime( 6 ),
                        rst.getDate( 7 )
                );
            }

            return null;
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(supplier_Id, 3) AS SIGNED)) AS max_value FROM supplier";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) {
            return rst.getInt(1);
        }

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT supplier_Id FROM supplier ORDER BY CAST(SUBSTRING(supplier_Id, 3) AS UNSIGNED)";
        ResultSet resultSet = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }

        return ids;
    }

    @Override
    public String getNameOfId(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT company_Name FROM supplier WHERE supplier_Id = ?",
                id
        );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public String getIdOfName(final String name) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT supplier_Id FROM supplier WHERE company_Name = ?",
                name
        );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }
}
