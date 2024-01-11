package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.ProductDto;

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
