package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.Supplier;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface SupplierDAO extends CrudUtil<Supplier> {
    String getNameOfId(final String id) throws SQLException;

    String getIdOfName(final String name) throws SQLException;
}
