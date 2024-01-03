package lk.lokitha.alokagreen.bo.custom;

import lk.lokitha.alokagreen.bo.SuperBO;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.dto.ProductDto;
import net.sf.jasperreports.engine.JRException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface CustomerOrderBO extends SuperBO {
    String getCustomerIdUsingMobile(String mobile) throws SQLException;
    boolean saveCustomerOrder(final CustomerOrderDto dto) throws SQLException;
    String getCustomerNameUsingMobile(String mobile) throws SQLException;
    ArrayList<String> getAllProductStockIdOfDesc(String desc) throws SQLException;
    String[] getProductStockDetails(String id) throws SQLException;
    ArrayList<String> getAllCustomerMobile() throws SQLException;
    ArrayList<String> getAllProductDesc() throws SQLException;
    String getProductId(String stockId) throws SQLException;
    String[] getDescAndPriceOfProduct(String id) throws SQLException;
    ArrayList<String> getAllCustomerOrderIds() throws SQLException;
    CustomerOrderDto getCustomerOrderDetails(String orderId) throws SQLException;
    CustomerDto getCustomerDetails(String id) throws SQLException;
    void saveCustomerOrderReportAsPDF(String orderId, Map<String, Object> map) throws JRException, SQLException;
    String getCustomerNameOfId(String id) throws SQLException;
    Map<String, String> getCustomerOrderDetailsData(String orderId) throws SQLException;
    ProductDto getProductDetails(String id) throws SQLException;
}
