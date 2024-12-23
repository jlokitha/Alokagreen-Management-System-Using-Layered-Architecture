package lk.ijse.alokagreen.dao.custom.impl;

import lk.ijse.alokagreen.dao.custom.MaterialDAO;
import lk.ijse.alokagreen.entity.MaterialList;
import lk.ijse.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public boolean save(final MaterialList entity) throws SQLException {
        return SQLUtil.execute( "INSERT INTO material_List VALUES(?, ?)",
                entity.getMaterialCode(),
                entity.getDescription()
        );
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(material_Code, 3) AS SIGNED)) AS max_value FROM material_List";

        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) return rst.getInt(1);

        return -1;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql= "SELECT material_Code FROM material_List ORDER BY CAST(SUBSTRING(material_Code, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> product = new ArrayList<>();

        while (rst.next()) {
            product.add(rst.getString(1));
        }

        return product;
    }

    @Override
    public ArrayList<String> getAllMaterialDesc() throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT description FROM material_List" );

        ArrayList<String> product = new ArrayList<>();

        while (rst.next()) {
            product.add(rst.getString(1));
        }

        return product;
    }

    @Override
    public String getDescOfId(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT description FROM material_List WHERE material_Code = ?", id );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public String getIdOfDesc(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT material_Code FROM material_List WHERE description = ?", id );

        if (rst.next()) {
            return rst.getString(1);
        }

        return null;
    }

    @Override
    public MaterialList getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM material_List WHERE material_Code = ?", id );

        if (rst.next()) {
            return new MaterialList(
                    rst.getString( 1 ),
                    rst.getString( 2 )
            );
        }

        return null;
    }

    @Override
    public boolean update(final MaterialList entity) throws SQLException {
        return SQLUtil.execute( "UPDATE material_List SET description=? WHERE material_Code = ?",
                entity.getDescription(),
                entity.getMaterialCode()
        );
    }

    @Override
    public boolean delete(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM material_List WHERE material_Code = ?", id );
    }
}
