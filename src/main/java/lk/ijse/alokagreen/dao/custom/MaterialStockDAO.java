package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.MaterialStock;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface MaterialStockDAO extends CrudUtil<MaterialStock> {
    boolean SaveMaterialStock( final String[] items, final String date) throws SQLException;
    boolean updateMaterialStockQty(final String... data) throws SQLException;
    int getProductQty(final String pId) throws SQLException;
    boolean UpdateMaterialStockExp(final String id) throws SQLException;
}
