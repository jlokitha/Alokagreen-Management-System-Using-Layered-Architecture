package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.dto.EmployeeAttendanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceDAO {
    boolean saveEmployeeAttendance(final EmployeeAttendanceDto dto) throws SQLException;
    boolean updateEmployeeAttendance(final String... data) throws SQLException;
    boolean deleteEmployeeAttendance(final String id) throws SQLException;
    int getLastId() throws SQLException;
    ArrayList<String> getAllId() throws SQLException;
    EmployeeAttendanceDto getData(final String id) throws SQLException;
    int getEmployeeWorkDayCount(final String id, final String yearMonth) throws SQLException;
    ArrayList<String> getAttendanceForDate(final String date) throws SQLException;
}
