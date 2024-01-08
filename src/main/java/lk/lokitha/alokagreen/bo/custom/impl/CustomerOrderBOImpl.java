package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.CustomerOrderBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.*;
import lk.lokitha.alokagreen.dao.custom.impl.*;
import lk.lokitha.alokagreen.db.DbConnection;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.dto.CustomerOrderDto;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.entity.Customer;
import lk.lokitha.alokagreen.entity.CustomerOrder;
import lk.lokitha.alokagreen.entity.ProductList;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.TransactionUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class CustomerOrderBOImpl implements CustomerOrderBO {
    private final CustomerDAO customerDAO = (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.CUSTOMER );
    private final CustomerOrderDAO customerOrderDAO = (CustomerOrderDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.CUSTOMER_ORDER );
    private final ProductStockDAO productStockDAO = (ProductStockDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.PRODUCT_STOCK );
    private final CustomerOrderDetailDAO customerOrderDetailDAO = (CustomerOrderDetailDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.CUSTOMER_ORDER_DETAIL );
    private final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.PRODUCT );
    private final QueryDAO queryDAO = (QueryDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.QUERY );

    @Override
    public String getCustomerIdUsingMobile(String mobile) throws SQLException {
        return customerDAO.getIdOfMobile( mobile );
    }

    @Override
    public boolean saveCustomerOrder(final CustomerOrderDto dto) throws SQLException {
        boolean result = false;

        try {
            TransactionUtil.startTransaction ();
            dto.setCustomer_Order_Id (NewId.newCustomerOrderId ());

            boolean isOrderSaved = customerOrderDAO.save (new CustomerOrder (
                    dto.getCustomer_Order_Id (),
                    dto.getCustomer_Id (),
                    dto.getTotal_Amount (),
                    DateTime.dateNow (),
                    DateTime.timeNow ()
            ));

            if (isOrderSaved) {

                boolean isQtyUpdated = productStockDAO.updateProductStock(dto.getItems());

                if (isQtyUpdated) {

                    boolean isAssociatedUpdate = customerOrderDetailDAO.saveCustomerOrderDetail(dto.getCustomer_Order_Id(), dto.getItems());

                    if (isAssociatedUpdate) {
                        result = true;
                    }

                }
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack ();
            e.printStackTrace ();
        } finally {
            TransactionUtil.endTransaction ();
        }

        return result;
    }

    @Override
    public String getCustomerNameUsingMobile(String mobile) throws SQLException {
        return customerDAO.getNameOfMobile ( mobile );
    }

    @Override
    public ArrayList<String> getAllProductStockIdOfDesc(String desc) throws SQLException {
        return productStockDAO.getAllIdOfPDesc ( desc );
    }

    @Override
    public String[] getProductStockDetails(String id) throws SQLException {
        return queryDAO.getStockDetails ( id );
    }

    @Override
    public ArrayList<String> getAllCustomerMobile() throws SQLException {
        return customerDAO.getAllMobile ();
    }

    @Override
    public ArrayList<String> getAllProductDesc() throws SQLException {
        return productDAO.getAllProductDesc ();
    }

    @Override
    public String getProductId(String stockId) throws SQLException {
        return productStockDAO.getProductId ( stockId );
    }

    @Override
    public String[] getDescAndPriceOfProduct(String id) throws SQLException {
        return productDAO.getDescUnitPriceOfId ( id );
    }

    @Override
    public ArrayList<String> getAllCustomerOrderIds() throws SQLException {
        return customerOrderDAO.getAllId ();
    }

    @Override
    public CustomerOrderDto getCustomerOrderDetails(String orderId) throws SQLException {
        CustomerOrder data = customerOrderDAO.getData ( orderId );

        return new CustomerOrderDto (
                data.getCustomerOrderId (),
                data.getCustomerId (),
                data.getTotalAmount (),
                data.getDate (),
                data.getTime ()
        );
    }

    @Override
    public CustomerDto getCustomerDetails(String id) throws SQLException {
        Customer data = customerDAO.getData ( id );

        return new CustomerDto (
                data.getCustomerId (),
                data.getName (),
                data.getMobile (),
                data.getEmail (),
                data.getAddress (),
                data.getDate (),
                data.getTime ()
        );
    }

    @Override
    public void saveCustomerOrderReportAsPDF(String orderId, Map<String, Object> map) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/CustomerOrderReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT" +
                "    cod.stock_Id," +
                "    cod.qty," +
                "    p.description," +
                "    p.unit_Price," +
                "    cod.qty * p.unit_Price AS total " +
                "FROM" +
                "    customer_Order co " +
                "JOIN " +
                "    customer_Order_Detail cod ON co.customer_Order_Id = cod.customer_Order_Id " +
                "JOIN " +
                "    product_Stock ps ON cod.stock_Id = ps.stock_Id " +
                "JOIN " +
                "    product_List p ON p.product_Code = ps.product_Code " +
                "WHERE " +
                "    co.customer_Order_Id = '" + orderId + "'");

        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                map,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/lokitha/Documents/Jasper/Customer Order PDF/" + orderId + ".pdf");
    }

    @Override
    public String getCustomerNameOfId(String id) throws SQLException {
        return customerDAO.getNameOfId ( id );
    }

    @Override
    public Map<String, String> getCustomerOrderDetailsData(String orderId) throws SQLException {
        return customerOrderDetailDAO.getData ( orderId );
    }

    @Override
    public ProductDto getProductDetails(String id) throws SQLException {
        ProductList data = productDAO.getData ( id );

        return new ProductDto (
                data.getProductCode (),
                data.getDescription (),
                data.getUnitPrice ()
        );
    }
}
