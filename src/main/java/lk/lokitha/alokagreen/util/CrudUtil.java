package lk.lokitha.alokagreen.util;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudUtil<T> {
    boolean save(final T entity) throws SQLException;

    boolean delete(final String id) throws SQLException;

    boolean update(final T entity) throws SQLException;

    T getData(final String id) throws SQLException;

    int getLastId() throws SQLException;

    ArrayList<String> getAllId() throws SQLException;
}
