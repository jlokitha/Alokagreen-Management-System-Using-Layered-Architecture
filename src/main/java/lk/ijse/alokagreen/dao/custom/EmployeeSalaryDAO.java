package lk.ijse.alokagreen.dao.custom;

import lk.ijse.alokagreen.entity.Salary;
import lk.ijse.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryDAO extends CrudUtil<Salary> {
    double getTotalSalary() throws SQLException;
    ArrayList<String> getSalaryForMonth(final String date) throws SQLException;
}
