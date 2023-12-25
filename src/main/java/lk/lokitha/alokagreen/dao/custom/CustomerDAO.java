package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudUtil<CustomerDto> {
    boolean saveCustomer(final CustomerDto customerDto) throws SQLException;

    int getLastId() throws SQLException;

    ArrayList<String> getAllId() throws SQLException;

    ArrayList<String> getAllMobile() throws SQLException;

    String getNameOfId(final String id) throws SQLException;

    String getNameOfMobile(final String mobile) throws SQLException;

    String getIdOfMobile(final String mobile) throws SQLException;

    CustomerDto getData(final String id) throws SQLException;

    boolean updateCustomer(final CustomerDto customerDto) throws SQLException;

    boolean deleteCustomer(final String id) throws SQLException;
}
