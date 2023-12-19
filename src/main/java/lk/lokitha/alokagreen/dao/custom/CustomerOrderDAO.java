package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.CustomerOrderDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerOrderDAO {
    String[] getStockDetails(final String sId) throws SQLException;

    int getLastId() throws SQLException;

    boolean saveCustomerOrder(final CustomerOrderDto customerOrderDto) throws SQLException;

    ArrayList<String> getAllId() throws SQLException;

    CustomerOrderDto getData(final String id) throws SQLException;

    boolean updateCustomerOrder(final String[] data) throws SQLException;

    double getTotalIncome() throws SQLException;
}
