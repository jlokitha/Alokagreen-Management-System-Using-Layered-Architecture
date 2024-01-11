package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.SpoiledReportBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.ProductDAO;
import lk.ijse.alokagreen.dao.custom.SpoiledReportDAO;
import lk.ijse.alokagreen.dao.custom.impl.ProductDAOImpl;
import lk.ijse.alokagreen.dao.custom.impl.SpoiledReportDAOImpl;
import lk.ijse.alokagreen.dto.SpoiledReportDto;
import lk.ijse.alokagreen.entity.SpoiledProductReport;
import lk.ijse.alokagreen.util.DateTime;
import lk.ijse.alokagreen.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;

public class SpoiledReportBOImpl implements SpoiledReportBO {
    private final SpoiledReportDAO spoiledReportDAO = (SpoiledReportDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.SPOILED );
    private final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory ().getDAO ( DAOFactory.DAOType.PRODUCT );

    @Override
    public boolean saveSpoiledReport( SpoiledReportDto dto ) throws SQLException {
        return spoiledReportDAO.save ( new SpoiledProductReport (
                NewId.newSpoiledReportId (),
                dto.getProduct_Code (),
                dto.getSpoiled_Qty (),
                DateTime.dateNow (),
                DateTime.timeNow ()
        ) );
    }

    @Override
    public String getProductId(String desc) throws SQLException {
        return productDAO.getIdOfDesc ( desc );
    }

    @Override
    public ArrayList<String> getAllProductDesc() throws SQLException {
        return productDAO.getAllProductDesc ();
    }

    @Override
    public String getProductDesc(String id) throws SQLException {
        return productDAO.getDescOfId ( id );
    }

    @Override
    public boolean updateSpoiledReport(String... data) throws SQLException {
        return spoiledReportDAO.UpdateSpoiledReport ( data );
    }

    @Override
    public SpoiledReportDto getSpoiledReportDetails(String id) throws SQLException {
        SpoiledProductReport data = spoiledReportDAO.getData ( id );

        return new SpoiledReportDto (
                data.getReportId (),
                data.getProductCode (),
                data.getSpoiledQty (),
                data.getDate (),
                data.getTime ()
        );
    }

    @Override
    public ArrayList<String> getAllSpoiledReportIds() throws SQLException {
        return spoiledReportDAO.getAllId ();
    }
}
