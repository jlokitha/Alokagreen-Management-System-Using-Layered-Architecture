package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.SupplierOrder;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface SupplierOrderDAO extends CrudUtil<SupplierOrder> {
    String getSupplierId(final String id) throws SQLException;
    double getTotalExpense() throws SQLException;
}
