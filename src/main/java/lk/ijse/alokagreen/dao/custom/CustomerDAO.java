package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.Customer;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudUtil<Customer> {

    ArrayList<String> getAllMobile() throws SQLException;

    String getNameOfId(final String id) throws SQLException;

    String getNameOfMobile(final String mobile) throws SQLException;

    String getIdOfMobile(final String mobile) throws SQLException;
}
