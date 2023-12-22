package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeSalaryDAO {
    boolean saveEmpSalary(final EmployeeSalaryDto dto) throws SQLException;
    boolean updateEmpSalary(final EmployeeSalaryDto dto) throws SQLException;
    boolean deleteSalary(final String id) throws SQLException;
    int getLastId() throws SQLException;
    ArrayList<String> getAllId() throws SQLException;
    EmployeeSalaryDto getData(final String id) throws SQLException;
    double getTotalSalary() throws SQLException;
    ArrayList<String> getSalaryForMonth(final String date) throws SQLException;
}
