package lk.lokitha.alokagreen.bo.custom.impl;

import lk.lokitha.alokagreen.bo.custom.CustomerBO;
import lk.lokitha.alokagreen.dao.DAOFactory;
import lk.lokitha.alokagreen.dao.custom.CustomerDAO;
import lk.lokitha.alokagreen.dao.custom.impl.CustomerDAOImpl;
import lk.lokitha.alokagreen.dto.CustomerDto;
import lk.lokitha.alokagreen.entity.Customer;
import lk.lokitha.alokagreen.util.NewId;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO = (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO( DAOFactory.DAOType.CUSTOMER );

    public boolean saveCustomer(CustomerDto dto) throws SQLException {
            return customerDAO.save( new Customer(
                    NewId.newCustomerId(),
                    dto.getName(),
                    dto.getMobile(),
                    dto.getEmail(),
                    dto.getAddress(),
                    String.valueOf( LocalDate.now() ),
                    String.valueOf( LocalTime.now() )
            ) );
    }

    public ArrayList<String> getAllCustomers() throws SQLException {
        return customerDAO.getAllId();
    }

    public CustomerDto getCustomerData(String id) throws SQLException {
        Customer data = customerDAO.getData( id );

        return new CustomerDto(
                data.getCustomerId(),
                data.getName(),
                data.getMobile(),
                data.getEmail(),
                data.getAddress(),
                data.getDate(),
                data.getTime()
        );
    }

    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete( id );
    }

    public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.update( new Customer(
                dto.getCustomer_Id(),
                dto.getName(),
                dto.getMobile(),
                dto.getEmail(),
                dto.getAddress(),
                dto.getDate(),
                dto.getTime()
        ) );
    }
}
