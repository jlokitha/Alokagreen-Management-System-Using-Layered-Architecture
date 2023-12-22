package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.ProductDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductDAO {
    boolean saveProduct(final ProductDto dto) throws SQLException;
    boolean updateProduct(final ProductDto dto) throws SQLException;
    boolean deleteProduct(final String id) throws SQLException;
    int getLastId() throws SQLException;
    ArrayList<String> getAllId() throws SQLException;
    ProductDto getData(final String id) throws SQLException;
    ArrayList<String> getAllProductDesc() throws SQLException;
    String getIdOfDesc(final String desc) throws SQLException;
    String getDescOfId(final String id) throws SQLException;
    String[] getDescUnitPriceOfId(final String id) throws SQLException;
}
