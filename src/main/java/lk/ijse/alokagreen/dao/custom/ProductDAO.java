package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.ProductList;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudUtil<ProductList> {
    ArrayList<String> getAllProductDesc() throws SQLException;
    String getIdOfDesc(final String desc) throws SQLException;
    String getDescOfId(final String id) throws SQLException;
    String[] getDescUnitPriceOfId(final String id) throws SQLException;
}
