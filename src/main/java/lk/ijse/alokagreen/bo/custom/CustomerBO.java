package lk.ijse.alokagreen.bo.custom;

import lk.ijse.alokagreen.bo.SuperBO;
import lk.ijse.alokagreen.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean saveCustomer(CustomerDto dto) throws SQLException;

    ArrayList<String> getAllCustomers() throws SQLException;

    CustomerDto getCustomerData(String id) throws SQLException;

    boolean deleteCustomer(String id) throws SQLException;

    boolean updateCustomer(CustomerDto dto) throws SQLException;
}
