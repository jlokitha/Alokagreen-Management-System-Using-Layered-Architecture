package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.dao.SuperDAO;

import java.sql.SQLException;

public interface QueryDAO extends SuperDAO {
    String[] getStockDetails(final String sId) throws SQLException;
}
