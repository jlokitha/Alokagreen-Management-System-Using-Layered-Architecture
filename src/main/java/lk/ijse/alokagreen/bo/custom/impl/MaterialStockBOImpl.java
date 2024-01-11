package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.MaterialStockBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.*;
import lk.ijse.alokagreen.dao.custom.impl.*;
import lk.ijse.alokagreen.dto.MaterialStockDto;
import lk.ijse.alokagreen.entity.MaterialStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaterialStockBOImpl implements MaterialStockBO {
    private final MaterialStockDAO materialStockDAO = (MaterialStockDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.MATERIAL_STOCK );
    private final MaterialDAO materialDAO = (MaterialDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.MATERIAL );
    private final SupplierDAO supplierDAO = (SupplierDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SUPPLIER );
    private final SupplierOrderDAO supplierOrderDAO = (SupplierOrderDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SUPPLIER_ORDER );
    private final SupplierOrderDetailDAO supplierOrderDetailDAO = (SupplierOrderDetailDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SUPPLIER_ORDER_DETAIL );

    @Override
    public ArrayList<String> getAllMaterialStockIds() throws SQLException {
        return materialStockDAO.getAllId();
    }

    @Override
    public boolean updateMaterialStockStatus(ArrayList<String> ids) throws SQLException {
        for (String id : ids) {
            boolean isUpdated = materialStockDAO.UpdateMaterialStockExp( id );

            if ( !isUpdated ) return false;
        }

        return true;
    }

    @Override
    public MaterialStockDto getMaterialStockDetail(String id) throws SQLException {
        MaterialStock data = materialStockDAO.getData( id );

        return new MaterialStockDto(
                data.getStockId(),
                data.getMaterialCode(),
                data.getQtyOnHand(),
                data.getQty(),
                data.getUnitPrice(),
                data.getDate(),
                data.getExpDate(),
                data.getStatus()
        );
    }

    @Override
    public String getMaterialDescription(String id) throws SQLException {
        return materialDAO.getDescOfId( id );
    }

    @Override
    public boolean updateMaterialStockQty(String... data) throws SQLException {
        return materialStockDAO.updateMaterialStockQty( data );
    }

    @Override
    public String getSupplierId(String stockId) throws SQLException {
        return supplierOrderDAO.getSupplierId( stockId );
    }

    @Override
    public String getSupplierName(String id) throws SQLException {
        return supplierDAO.getNameOfId( id );
    }

    @Override
    public String getSupplierOrderId(String stockId) throws SQLException {
        return supplierOrderDetailDAO.getOrderId( stockId );
    }
}
