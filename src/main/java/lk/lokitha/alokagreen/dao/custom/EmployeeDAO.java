package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.Employee;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudUtil<Employee> {
    String getNameOfId(final String id) throws SQLException;
    String getEmailOfId(final String id) throws SQLException;
}
