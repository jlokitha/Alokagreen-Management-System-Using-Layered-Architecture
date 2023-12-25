package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.MaterialList;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialDAO extends CrudUtil<MaterialList> {
    ArrayList<String> getAllMaterialDesc() throws SQLException;
    String getDescOfId(final String id) throws SQLException;
    String getIdOfDesc(final String id) throws SQLException;
}
