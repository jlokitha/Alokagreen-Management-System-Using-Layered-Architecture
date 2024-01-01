package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.MaterialStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaterialStockBO extends SuperBO {
    ArrayList<String> getAllMaterialStockIds() throws SQLException;
    boolean updateMaterialStockStatus(ArrayList<String> ids) throws SQLException;
    MaterialStockDto getMaterialStockDetail(String id) throws SQLException;
    String getMaterialDescription(String id) throws SQLException;
    boolean updateMaterialStockQty(String... data) throws SQLException;
    String getSupplierId(String stockId) throws SQLException;
    String getSupplierName(String id) throws SQLException;
    String getSupplierOrderId(String stockId) throws SQLException;
}
