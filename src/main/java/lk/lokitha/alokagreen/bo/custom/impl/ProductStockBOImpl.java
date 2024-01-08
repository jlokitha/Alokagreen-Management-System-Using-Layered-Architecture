package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.ProductStockBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.ProductDAO;
import lk.lokitha.alokagreen.dao.custom.ProductStockDAO;
import lk.lokitha.alokagreen.dao.custom.SpoiledReportDAO;
import lk.lokitha.alokagreen.dao.custom.impl.ProductDAOImpl;
import lk.lokitha.alokagreen.dao.custom.impl.ProductStockDAOImpl;
import lk.lokitha.alokagreen.dao.custom.impl.SpoiledReportDAOImpl;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.dto.ProductStockDto;
import lk.lokitha.alokagreen.entity.ProductList;
import lk.lokitha.alokagreen.entity.ProductStock;
import lk.lokitha.alokagreen.entity.SpoiledProductReport;
import lk.lokitha.alokagreen.util.DateTime;
import lk.lokitha.alokagreen.util.NewId;
import lk.lokitha.alokagreen.util.TransactionUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProductStockBOImpl implements ProductStockBO {
    private final ProductStockDAO productStockDAO = (ProductStockDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.PRODUCT_STOCK );
    private final SpoiledReportDAO spoiledReportDAO = (SpoiledReportDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.SPOILED );
    private final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.PRODUCT );

    @Override
    public String saveProductStock(ProductStockDto dto) throws SQLException {
        LocalDate expDate = LocalDate.parse( dto.getExp_Date( ) );
        LocalDate now = LocalDate.now();
        String status;

        if (expDate.isBefore(now) || expDate.isEqual(now)) {
            status = "Expired";
        } else {
            status = "Not Expired";
        }

        boolean save = productStockDAO.save( new ProductStock(
                NewId.newProductStockCode( ),
                dto.getProduct_Code( ),
                dto.getQty_On_Hand( ),
                dto.getQty( ),
                DateTime.dateNow( ),
                dto.getExp_Date( ),
                status
        ) );
        return save ? status : null;
    }

    @Override
    public boolean saveSpoiledProductReport(String pId, int qty) throws SQLException {
        return spoiledReportDAO.save( new SpoiledProductReport(
                new NewId().newSpoiledReportId(),
                pId,
                qty,
                DateTime.dateNow(),
                DateTime.timeNow()
        ) );
    }

    @Override
    public String getProductId(String desc) throws SQLException {
        return productDAO.getIdOfDesc( desc );
    }

    @Override
    public ArrayList<String> getAllProductDescription() throws SQLException {
        return productDAO.getAllProductDesc();
    }

    @Override
    public ArrayList<String> getAllProductStockIds() throws SQLException {
        return productStockDAO.getAllId();
    }

    @Override
    public boolean updateProductStockExp(final ArrayList<String> ids) throws SQLException {
        boolean result = false;

        try {
            TransactionUtil.startTransaction();

            boolean updated = productStockDAO.UpdateProductStockExp(ids);

            if ( updated ) {
                boolean save = true;

                for (String id : ids) {

                    boolean added = spoiledReportDAO.save( new SpoiledProductReport(
                            NewId.newSpoiledReportId( ),
                            productStockDAO.getProductId( id ),
                            productStockDAO.getProductQtyOnHand( id ),
                            DateTime.dateNow( ),
                            DateTime.timeNow( )
                    ) );

                    if (!added) {
                        save = false;
                    }
                }

                if ( save ) {
                    TransactionUtil.endTransaction();
                    result = true;
                }
            }
        } catch (SQLException e) {
            TransactionUtil.rollBack();
        }
        return result;
    }

    @Override
    public ProductStockDto getProductStockDetails(String id) throws SQLException {
        ProductStock data = productStockDAO.getData( id );

        return new ProductStockDto(
                data.getStockId(),
                data.getProductCode(),
                data.getQtyOnHand(),
                data.getQty(),
                data.getDate(),
                data.getExpDate(),
                data.getStatus()
        );
    }

    @Override
    public String[] getProductDescAndPrice(String id) throws SQLException {
        return productDAO.getDescUnitPriceOfId( id );
    }

    @Override
    public boolean updateProductStock(ProductStockDto dto) throws SQLException {
        return productStockDAO.update( new ProductStock(
                dto.getStock_Id(),
                dto.getProduct_Code(),
                dto.getQty_On_Hand(),
                dto.getQty(),
                dto.getDate(),
                dto.getExp_Date(),
                dto.getStatus()
        ) );
    }

    @Override
    public String getProductDescription(String id) throws SQLException {
        return productDAO.getDescOfId( id );
    }

    @Override
    public ProductDto getProductDetails(String id) throws SQLException {
        ProductList data = productDAO.getData( id );

        return new ProductDto(
                data.getProductCode(),
                data.getDescription(),
                data.getUnitPrice()
        );
    }
}
