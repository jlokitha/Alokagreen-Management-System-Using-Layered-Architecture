package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.MaterialDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialBO extends SuperBO {
    boolean saveMaterial(MaterialDto dto) throws SQLException;
    ArrayList<String> getAllMaterialIds() throws SQLException;
    boolean deleteMaterial(String id) throws SQLException;
    MaterialDto getMaterialData(String id) throws SQLException;
    boolean updateMaterial(MaterialDto dto) throws SQLException;
    String generateNewMaterialId() throws SQLException;
}
