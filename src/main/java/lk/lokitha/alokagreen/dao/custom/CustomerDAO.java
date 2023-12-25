package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.entity.Customer;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudUtil<Customer> {

    ArrayList<String> getAllMobile() throws SQLException;

    String getNameOfId(final String id) throws SQLException;

    String getNameOfMobile(final String mobile) throws SQLException;

    String getIdOfMobile(final String mobile) throws SQLException;
}
