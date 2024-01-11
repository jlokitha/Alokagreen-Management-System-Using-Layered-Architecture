package lk.ijse.alokagreen.bo.custom.impl;

import lk.ijse.alokagreen.bo.custom.ProductBO;
import lk.ijse.alokagreen.dao.DAOFactory;
import lk.ijse.alokagreen.dao.custom.ProductDAO;
import lk.ijse.alokagreen.dao.custom.impl.ProductDAOImpl;
import lk.ijse.alokagreen.dto.ProductDto;
import lk.ijse.alokagreen.entity.ProductList;
import lk.ijse.alokagreen.util.NewId;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    private final ProductDAO productDAO = (ProductDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.PRODUCT );

    @Override
    public boolean saveProduct(ProductDto dto) throws SQLException {
        return productDAO.save( new ProductList(
                dto.getProduct_Code(),
                dto.getDescription(),
                dto.getUnit_Price()
        ) );
    }

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

    @Override
    public String generateNewProductId() throws SQLException {
        return NewId.newProductCode();
    }
}
