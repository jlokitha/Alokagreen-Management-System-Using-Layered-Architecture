package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.MaterialStock;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialStockDAO extends CrudUtil<MaterialStock> {
    boolean SaveMaterialStock( final String[] items, final String date) throws SQLException;
    boolean updateMaterialStockQty(final String... data) throws SQLException;
    int getProductQty(final String pId) throws SQLException;
    boolean UpdateMaterialStockExp(final String id) throws SQLException;
}
