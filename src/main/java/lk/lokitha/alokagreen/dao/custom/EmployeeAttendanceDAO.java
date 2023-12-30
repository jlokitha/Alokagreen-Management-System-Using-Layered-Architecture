package lk.lokitha.alokagreen.dao.custom;

import lk.lokitha.alokagreen.entity.Attendance;
import lk.lokitha.alokagreen.util.CrudUtil;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeAttendanceDAO extends CrudUtil<Attendance> {
    int getEmployeeWorkDayCount(final String id, final String yearMonth) throws SQLException;
    ArrayList<String> getAttendanceForDate(final String date) throws SQLException;
}
