package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.MaterialStockDto;
import lk.lokitha.alokagreen.dto.tm.MaterialStockTm;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialStockDAO {
    boolean SaveMaterialStock(final String[] item, final String date) throws SQLException;
    boolean updateMaterialStock(final String... data) throws SQLException;
    boolean deleteMaterialStock(final String id) throws SQLException;
    int getLastId() throws SQLException;
    MaterialStockTm getData(final String id) throws SQLException;
    MaterialStockDto getDetail(final String id) throws SQLException;
    ArrayList<String> getAllId() throws SQLException;
    int getProductQty(final String pId) throws SQLException;
    boolean UpdateMaterialStockExp(final String id) throws SQLException;
}
