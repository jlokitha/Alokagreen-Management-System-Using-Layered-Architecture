package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    String[] getStockDetails(final String sId) throws SQLException;
}
