package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.entity.ProductList;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO extends CrudUtil<ProductList> {
    ArrayList<String> getAllProductDesc() throws SQLException;
    String getIdOfDesc(final String desc) throws SQLException;
    String getDescOfId(final String id) throws SQLException;
    String[] getDescUnitPriceOfId(final String id) throws SQLException;
}
