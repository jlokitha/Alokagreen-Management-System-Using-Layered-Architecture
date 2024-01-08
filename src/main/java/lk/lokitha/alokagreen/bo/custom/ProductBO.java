package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.ProductDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductBO extends SuperBO {
    boolean saveProduct(ProductDto dto) throws SQLException;
    ArrayList<String> getAllProductIds() throws SQLException;
    boolean deleteProduct(String code) throws SQLException;
    ProductDto getProductData(String code) throws SQLException;
    boolean updateProduct(ProductDto dto) throws SQLException;
    String generateNewProductId() throws SQLException;
}
