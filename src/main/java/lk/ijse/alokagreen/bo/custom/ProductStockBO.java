package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.ProductDto;
import lk.ijse.alokagreen.dto.ProductStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ProductStockBO extends SuperBO {
    String saveProductStock(ProductStockDto dto) throws SQLException;
    boolean saveSpoiledProductReport(String pId, int qty) throws SQLException;
    String getProductId(String id) throws SQLException;
    ArrayList<String> getAllProductDescription() throws SQLException;
    ArrayList<String> getAllProductStockIds() throws SQLException;
    boolean updateProductStockExp(final ArrayList<String> ids) throws SQLException;
    ProductStockDto getProductStockDetails(String id) throws SQLException;
    String[] getProductDescAndPrice(String id) throws SQLException;
    boolean updateProductStock(ProductStockDto dto) throws SQLException;
    String getProductDescription(String id) throws SQLException;
    ProductDto getProductDetails(String id) throws SQLException;
}
