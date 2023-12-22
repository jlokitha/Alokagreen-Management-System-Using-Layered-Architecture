package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.MaterialDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialDAO {
    boolean saveMaterial(final MaterialDto dto) throws SQLException;
    boolean updateMaterial(final MaterialDto dto) throws SQLException;
    boolean deleteMaterial(final String id) throws SQLException;
    int getLastId() throws SQLException;
    MaterialDto getData(final String id) throws SQLException;
    ArrayList<String> getAllMaterialId() throws SQLException;
    ArrayList<String> getAllMaterialDesc() throws SQLException;
    String getDescOfId(final String id) throws SQLException;
    String getIdOfDesc(final String id) throws SQLException;
}
