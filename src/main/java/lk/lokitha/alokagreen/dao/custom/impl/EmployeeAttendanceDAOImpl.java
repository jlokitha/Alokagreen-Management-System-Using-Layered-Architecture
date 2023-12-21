package lk.lokitha.alokagreen.dao.custom.impl;

import lk.lokitha.alokagreen.dao.custom.EmployeeAttendanceDAO;
import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;
import lk.lokitha.alokagreen.util.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeAttendanceDAOImpl implements EmployeeAttendanceDAO {
    @Override
    public boolean saveEmployeeAttendance(final EmployeeAttendanceDto dto) throws SQLException {
        return SQLUtil.execute( "INSERT INTO attendance VALUES(?, ?, ?, ?)",
                dto.getAttendance_Id(),
                dto.getEmployee_Id(),
                dto.getDate(),
                dto.getTime()
        );
    }

    @Override
    public int getEmployeeWorkDayCount(final String id, final String yearMonth) throws SQLException {
        String sql = "SELECT COUNT(*) AS work_days FROM attendance WHERE employee_Id = ?  AND date LIKE ?";
        ResultSet rst = SQLUtil.execute( sql, id, (yearMonth + "%") );

        if (rst.next()) {
            return rst.getInt(1);
        }

        return 0;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException {
        String sql = "SELECT attendance_Id FROM attendance ORDER BY CAST(SUBSTRING(attendance_Id, 3) AS UNSIGNED)";
        ResultSet rst = SQLUtil.execute( sql );

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }

    @Override
    public EmployeeAttendanceDto getData(final String id) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT * FROM attendance WHERE attendance_Id = ?", id);

        if (rst.next()) {
            return new EmployeeAttendanceDto(
                    rst.getString( 1 ),
                    rst.getString( 2 ),
                    rst.getString( 3 ),
                    rst.getString( 4 )
            );
        }

        return null;
    }

    @Override
    public int getLastId() throws SQLException {
        String sql = "SELECT MAX(CAST(SUBSTRING(attendance_Id, 4) AS SIGNED)) AS max_value FROM attendance";
        ResultSet rst = SQLUtil.execute( sql );

        if (rst.next()) {
            return rst.getInt(1);
        }

        return -1;
    }

    @Override
    public boolean updateEmployeeAttendance(final String... data) throws SQLException {
        return SQLUtil.execute( "UPDATE attendance SET employee_Id=? WHERE attendance_Id = ?",
                data[1],
                data[0]
        );
    }

    @Override
    public boolean deleteEmployeeAttendance(final String id) throws SQLException {
        return SQLUtil.execute( "DELETE FROM attendance WHERE attendance_Id = ?", id );
    }

    @Override
    public ArrayList<String> getAttendanceForDate(final String date) throws SQLException {
        ResultSet rst = SQLUtil.execute( "SELECT attendance_Id FROM attendance WHERE date = ?", date);

        ArrayList<String> ids = new ArrayList<>();

        while (rst.next()) {
            ids.add(rst.getString(1));
        }

        return ids;
    }
}
