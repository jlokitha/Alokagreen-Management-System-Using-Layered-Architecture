package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.Employee;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudUtil<Employee> {
    String getNameOfId(final String id) throws SQLException;
    String getEmailOfId(final String id) throws SQLException;
}
