package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.MaterialDAO;
import lk.lokitha.alokagreen.dto.MaterialDto;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialDAOImpl implements MaterialDAO {
    @Override
    public boolean saveMaterial(final MaterialDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO material_List VALUES(?, ?)",
                dto.getMaterial_Code(),
                dto.getDescription()
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
    public ArrayList<String> getAllMaterialId() throws SQLException {
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
    public MaterialDto getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM material_List WHERE material_Code = ?", id );

        if (rst.next()) {
            return new MaterialDto(
                    rst.getString( 1 ),
                    rst.getString( 2 )
            );
        }

        return null;
    }

    @Override
    public boolean updateMaterial(final MaterialDto dto) throws SQLException {
        return SQLUtil.execute( "UPDATE material_List SET description=? WHERE material_Code = ?",
                dto.getDescription(),
                dto.getMaterial_Code()
        );
    }

    @Override
    public boolean deleteMaterial(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM material_List WHERE material_Code = ?", id );
    }
}
