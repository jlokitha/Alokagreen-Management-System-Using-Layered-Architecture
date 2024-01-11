package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.SupplierOrderBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.*;
import lk.ijse.alokagreen.dao.custom.impl.*;
import lk.ijse.alokagreen.db.DbConnection;
import lk.ijse.alokagreen.dto.MaterialDto;
import lk.ijse.alokagreen.dto.MaterialStockDto;
import lk.ijse.alokagreen.dto.SupplierDto;
import lk.ijse.alokagreen.dto.SupplierOrderDto;
import lk.ijse.alokagreen.entity.MaterialList;
import lk.ijse.alokagreen.entity.MaterialStock;
import lk.ijse.alokagreen.entity.Supplier;
import lk.ijse.alokagreen.entity.SupplierOrder;
import lk.ijse.alokagreen.util.NewId;
import lk.ijse.alokagreen.util.TransactionUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class SupplierOrderBOImpl implements SupplierOrderBO {
    private final SupplierDAO supplierDAO = (SupplierDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SUPPLIER );
    private final SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SUPPLIER_ORDER );
    private final MaterialDAO materialDAO = (MaterialDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.MATERIAL );
    private final SupplierOrderDetailDAO supplierOrderDetailDAO = (SupplierOrderDetailDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SUPPLIER_ORDER_DETAIL );
    private final MaterialStockDAO materialStockDAO = (MaterialStockDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.MATERIAL_STOCK );

    @Override
    public String getSupplierIdOfName(String name) throws SQLException {
        return supplierDAO.getIdOfName ( name );
    }

    @Override
    public boolean saveSupplierOrder(final SupplierOrderDto dto) throws SQLException {
        boolean result = false;

        try {
            TransactionUtil.startTransaction ();

            boolean isOrderSaved = supplierOrderDAO.save (new SupplierOrder (
                    dto.getSupplier_Order_Id (),
                    dto.getSupplier_Id (),
                    dto.getTotal_Amount (),
                    dto.getDate (),
                    dto.getTime ()
            ));

            if (isOrderSaved) {

                boolean isStockSaved = true;

                for (String[] item : dto.getItems ()) {
                    item[0] = NewId.newMaterialStockCode ();
                    boolean s = materialStockDAO.SaveMaterialStock ( item, dto.getDate ( ) );

                    if ( !s ) {
                        isStockSaved = false;
                        break;
                    }
                }

                if (isStockSaved) {

                    boolean isAssociateSaved = supplierOrderDetailDAO.saveSupplierOrderDetail(dto.getSupplier_Order_Id(), dto.getItems());

                    if (isAssociateSaved) {
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
    public String getMaterialIdOfDesc(String desc) throws SQLException {
        return materialDAO.getIdOfDesc ( desc );
    }

    @Override
    public String getIdOfName(String name) throws SQLException {
        return supplierDAO.getIdOfName ( name );
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException {
        return supplierDAO.getAllId ();
    }

    @Override
    public String getSupplierNameOfId(String id) throws SQLException {
        return supplierDAO.getNameOfId ( id );
    }

    @Override
    public ArrayList<String> getAllMaterialDesc() throws SQLException {
        return materialDAO.getAllMaterialDesc ();
    }

    @Override
    public String getMaterialDeskOfId(String id) throws SQLException {
        return materialDAO.getDescOfId ( id );
    }

    @Override
    public ArrayList<String> getAllSupplierOrderIds() throws SQLException {
        return supplierOrderDAO.getAllId ();
    }

    @Override
    public SupplierOrderDto getSupplierOrderDetails(String id) throws SQLException {
        SupplierOrder data = supplierOrderDAO.getData ( id );

        return new SupplierOrderDto (
                data.getSupplierOrderId (),
                data.getSupplierId (),
                data.getTotalAmount (),
                data.getDate (),
                data.getTime (),
                null
        );
    }

    @Override
    public SupplierDto getSupplierDetails( String id) throws SQLException {
        Supplier data = supplierDAO.getData ( id );

        return new SupplierDto (
                data.getSupplierId (),
                data.getCompanyName (),
                data.getCompanyEmail (),
                data.getCompanyMobile (),
                data.getCompanyLocation (),
                data.getTime (),
                data.getDate ()
        );
    }

    @Override
    public void saveSupplierOrderAsPDF( String orderId, Map<String, Object> map ) throws JRException, SQLException {
        InputStream resourceAsStream = getClass().getResourceAsStream("/report/SupplierOrderReport.jrxml");
        JasperDesign load = JRXmlLoader.load(resourceAsStream);

        JRDesignQuery jrDesignQuery = new JRDesignQuery();
        jrDesignQuery.setText("SELECT" +
                "    sod.stock_Id," +
                "    ms.qty," +
                "    m.description," +
                "    ms.unit_Price," +
                "    ms.qty * ms.unit_Price AS total " +
                "FROM " +
                "    supplier_Order so " +
                "JOIN " +
                "    supplier_Order_Detail sod ON so.supplier_Order_Id = sod.supplier_Order_Id " +
                "JOIN " +
                "    material_Stock ms ON sod.stock_Id = ms.stock_Id " +
                "JOIN " +
                "    material_List m ON m.material_Code = ms.material_Code " +
                "WHERE " +
                "    so.supplier_Order_Id ='" + orderId + "'");

        load.setQuery(jrDesignQuery);

        JasperReport jasperReport = JasperCompileManager.compileReport(load);

        JasperPrint jasperPrint = JasperFillManager.fillReport(
                jasperReport,
                map,
                DbConnection.getInstance().getConnection()
        );

        JasperViewer.viewReport(jasperPrint, false);
        JasperExportManager.exportReportToPdfFile(jasperPrint, "/home/lokitha/Documents/Jasper/" + orderId + ".pdf");
    }

    @Override
    public ArrayList<String> getSupplierOrderDetailsData(String id) throws SQLException {
        return supplierOrderDetailDAO.getData ( id );
    }

    @Override
    public MaterialStockDto getMaterialStockDetails(String id) throws SQLException {
        MaterialStock data = materialStockDAO.getData ( id );

        return new MaterialStockDto (
                data.getStockId (),
                data.getMaterialCode (),
                data.getQtyOnHand (),
                data.getQty (),
                data.getUnitPrice (),
                data.getDate (),
                data.getExpDate (),
                data.getStatus ()
        );
    }

    @Override
    public MaterialDto getMaterialDetails(String id) throws SQLException {
        MaterialList data = materialDAO.getData ( id );

        return new MaterialDto (
                data.getMaterialCode (),
                data.getDescription ()
        );
    }
}
