package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.SupplierOrder;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface SupplierOrderDAO extends CrudUtil<SupplierOrder> {
    String getSupplierId(final String id) throws SQLException;
    double getTotalExpense() throws SQLException;
}
