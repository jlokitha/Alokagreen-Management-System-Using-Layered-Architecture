package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.ProductStock;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface ProductStockDAO extends CrudUtil<ProductStock> {
    boolean updateProductStock(final Map<String, String> items) throws SQLException;

    ArrayList<String> getAllIdOfPDesc(final String pDesc) throws SQLException;

    String getProductId(final String stockId) throws SQLException;

    int getProductQty(final String pId) throws SQLException;

    boolean UpdateProductStockExp(final ArrayList<String> ids) throws SQLException;

    int getProductQtyOnHand(final String pId) throws SQLException;
}
