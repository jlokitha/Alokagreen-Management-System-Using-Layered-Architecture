package lk.lokitha.alokagreen.bo.custom.impl;

import javafx.fxml.FXML;
import lk.lokitha.alokagreen.bo.custom.SupplierBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.SupplierDAO;
import lk.lokitha.alokagreen.dao.custom.impl.SupplierDAOImpl;
import lk.lokitha.alokagreen.dto.SupplierDto;
import lk.lokitha.alokagreen.entity.Supplier;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    private final SupplierDAO supplierDAO = (SupplierDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SUPPLIER );

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save( new Supplier(
                NewId.newSupplierId(),
                dto.getCompany_Name(),
                dto.getCompany_Email(),
                dto.getCompany_Mobile(),
                dto.getCompany_Location(),
                DateTime.timeNow(),
                DateTime.dateNow()
        ) );
    }

    @Override
    public ArrayList<String> getAllSupplierIds() throws SQLException {
        return supplierDAO.getAllId( );
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete( id );
    }

    @Override
    public SupplierDto getSupplierData(String id) throws SQLException {
        Supplier data = supplierDAO.getData( id );

        return new SupplierDto(
                data.getSupplierId(),
                data.getCompanyName(),
                data.getCompanyEmail(),
                data.getCompanyMobile(),
                data.getCompanyLocation(),
                data.getDate(),
                data.getTime()
        );
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update( new Supplier(
                dto.getSupplier_Id(),
                dto.getCompany_Name(),
                dto.getCompany_Email(),
                dto.getCompany_Mobile(),
                dto.getCompany_Location(),
                dto.getTime(),
                dto.getDate()
        ) );
    }
}
