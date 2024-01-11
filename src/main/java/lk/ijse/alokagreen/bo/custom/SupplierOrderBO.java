package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.MaterialDto;
import lk.ijse.alokagreen.dto.MaterialStockDto;
import lk.ijse.alokagreen.dto.SupplierDto;
import lk.ijse.alokagreen.dto.SupplierOrderDto;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface SupplierOrderBO extends SuperBO {
    String getSupplierIdOfName(String name) throws SQLException;
    boolean saveSupplierOrder(final SupplierOrderDto dto) throws SQLException;
    String getMaterialIdOfDesc(String desc) throws SQLException;
    String getIdOfName(String name) throws SQLException;
    ArrayList<String> getAllSupplierIds() throws SQLException;
    String getSupplierNameOfId(String id) throws SQLException;
    ArrayList<String> getAllMaterialDesc() throws SQLException;
    String getMaterialDeskOfId(String id) throws SQLException;
    ArrayList<String> getAllSupplierOrderIds() throws SQLException;
    SupplierOrderDto getSupplierOrderDetails(String id) throws SQLException;
    SupplierDto getSupplierDetails( String id) throws SQLException;
    void saveSupplierOrderAsPDF( String orderId, Map<String, Object> map ) throws JRException, SQLException;
    ArrayList<String> getSupplierOrderDetailsData(String id) throws SQLException;
    MaterialStockDto getMaterialStockDetails( String id) throws SQLException;
    MaterialDto getMaterialDetails( String id) throws SQLException;
}
