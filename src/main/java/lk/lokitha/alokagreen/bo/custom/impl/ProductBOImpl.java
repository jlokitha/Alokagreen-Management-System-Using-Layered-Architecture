package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.ProductBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.ProductDAO;
import lk.lokitha.alokagreen.dao.custom.impl.ProductDAOImpl;
import lk.lokitha.alokagreen.dto.ProductDto;
import lk.lokitha.alokagreen.entity.ProductList;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    private final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.PRODUCT );

    @Override
    public ArrayList<String> getAllProductIds() throws SQLException {
        return productDAO.getAllId();
    }

    @Override
    public boolean deleteProduct(String code) throws SQLException {
        return productDAO.delete( code );
    }

    @Override
    public ProductDto getProductData(String code) throws SQLException {
        ProductList data = productDAO.getData( code );

        return new ProductDto(
                data.getProductCode(),
                data.getDescription(),
                data.getUnitPrice()
        );
    }

    @Override
    public boolean updateProduct(ProductDto dto) throws SQLException {
        return productDAO.update( new ProductList(
                dto.getProduct_Code(),
                dto.getDescription(),
                dto.getUnit_Price()
        ) );
    }
}
