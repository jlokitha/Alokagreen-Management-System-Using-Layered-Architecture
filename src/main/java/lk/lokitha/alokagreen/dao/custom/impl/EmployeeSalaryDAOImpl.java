package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.EmployeeSalaryDAO;
import lk.lokitha.alokagreen.dto.EmployeeSalaryDto;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeSalaryDAOImpl implements EmployeeSalaryDAO {
    @Override
    public boolean saveEmpSalary(final EmployeeSalaryDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO salary VALUES(?, ?, ?, ?, ?, ?, ?)",
                dto.getSalary_Id(),
                dto.getEmployee_Id(),
                dto.getTotal_Salary(),
                dto.getWorked_Days(),
                dto.getBonus(),
                dto.getDate(),
                dto.getTime()
        );
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT salary_Id FROM salary ORDER BY date DESC, time DESC" );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public EmployeeSalaryDto getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM salary WHERE salary_Id  = ?", id );

        if (rst.next()) {
            return new EmployeeSalaryDto(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getDouble( 3 ),
                    rst.getInt( 4 ),
                    rst.getDouble( 5 ),
                    rst.getString( 6 ),
                    rst.getString( 7 )
            );
        }

        return null;
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(salary_Id, 4) AS SIGNED)) AS max_value FROM salary";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) {
            return rst.getInt(1);
        }

        return -1;
    }

    @Override
    public boolean updateEmpSalary(final EmployeeSalaryDto dto) throws SQLException {
        String sql = "UPDATE salary SET employee_Id=?, total_Salary=?, work_Day_Count=?, bonus=? WHERE salary_Id = ?";

        return SQLUtil.execute( sql,
                dto.getEmployee_Id(),
                dto.getTotal_Salary(),
                dto.getWorked_Days(),
                dto.getBonus(),
                dto.getSalary_Id()
        );
    }

    @Override
    public boolean deleteSalary(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM salary WHERE salary_Id = ?", id );
    }

    @Override
    public double getTotalSalary() throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT SUM(total_Salary) AS total_salary_sum FROM salary" );

        if (rst.next()) return rst.getDouble(1);

        return 0.00;
    }

    @Override
    public ArrayList<String> getSalaryForMonth(final String date) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT salary_Id FROM salary WHERE date LIKE ?" );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }
}
