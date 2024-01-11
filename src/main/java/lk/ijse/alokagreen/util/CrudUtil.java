package lk.ijse.alokagreen.util;

import lk.ijse.alokagreen.dao.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudUtil<T> extends SuperDAO {
    boolean save(final T entity) throws SQLException;

    boolean delete(final String id) throws SQLException;

    boolean update(final T entity) throws SQLException;

    T getData(final String id) throws SQLException;

    int getLastId () throws SQLException;

    ArrayList<String> getAllId() throws SQLException;
}
