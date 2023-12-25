package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.Salary;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryDAO extends CrudUtil<Salary> {
    double getTotalSalary() throws SQLException;
    ArrayList<String> getSalaryForMonth(final String date) throws SQLException;
}
