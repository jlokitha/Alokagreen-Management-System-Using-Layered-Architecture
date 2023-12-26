package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.Supplier;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface SupplierDAO extends CrudUtil<Supplier> {
    String getNameOfId(final String id) throws SQLException;

    String getIdOfName(final String name) throws SQLException;
}
